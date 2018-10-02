package com.example.pinheiro.serfeliz;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pinheiro.serfeliz.domain.Address;
import com.example.pinheiro.serfeliz.domain.Util;
import com.example.pinheiro.serfeliz.domain.ZipCodeListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pojos.DadosGeraisProfissionalFisica;
import util.MaskEditUtil;


public class SignUpActivity extends AppCompatActivity {
    private EditText etZipCode;
    private Util util;
    Button btnEnviaCadastroProfissional;

    protected AlertDialog alerta;
    EditText edtNomeProfissional;
    EditText edtCelularProfissional;
    EditText edtRuaProfissional;
    EditText edtNumeroProfissional;
    EditText edtComplementoProfissional;
    EditText edtBairroProfissional;
    EditText edtCidadeProfissional;

    private List<String> tiposDeFestas = new ArrayList<String>();
    ArrayAdapter<String> adapterFestas;
    Spinner listaSpinner;
    private FirebaseFirestore mFirestore;
    String estado;



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_tela_cadastro,new FragmentResumo())
                .addToBackStack(null)
                .commit();




        //  startActivity(new Intent(SignUpActivity.this,Tela));

    //    finish();

        btnEnviaCadastroProfissional  = (Button) findViewById(R.id.btn_Envia_Cadastro_Profissional);

        etZipCode = (EditText) findViewById(R.id.et_zip_code);
        etZipCode.addTextChangedListener( new ZipCodeListener( this ) );

        edtNomeProfissional = (EditText) findViewById(R.id.edt_Nome_Profissional);
        edtCelularProfissional = (EditText) findViewById(R.id.edt_Celular_Profissional);
        edtRuaProfissional = (EditText) findViewById(R.id.et_street);
        edtComplementoProfissional = (EditText) findViewById(R.id.et_complement);
        edtNumeroProfissional = (EditText) findViewById(R.id.et_number);
        edtBairroProfissional = (EditText) findViewById(R.id.et_neighbor);
        edtCidadeProfissional = (EditText) findViewById(R.id.et_city);
        listaSpinner = (Spinner) findViewById(R.id.sp_state);

        edtCelularProfissional.addTextChangedListener(
                         MaskEditUtil.mask(edtCelularProfissional,MaskEditUtil.FORMAT_FONE));



        adapterFestas =   new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, R.array.states);
        adapterFestas.setDropDownViewResource(android.R.layout.simple_spinner_item);
        listaSpinner.setAdapter(adapterFestas);

        mFirestore = FirebaseFirestore.getInstance();

        listaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int posicao, long l) {

                estado = parent.getItemAtPosition(posicao).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        Spinner spStates = (Spinner) findViewById(R.id.sp_state);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this,
                        R.array.states,
                        android.R.layout.simple_spinner_item);
        spStates.setAdapter(adapter);

        util = new Util(this,
                R.id.et_zip_code,
                R.id.et_street,
                R.id.et_complement,
                R.id.et_neighbor,
                R.id.et_city,
                R.id.sp_state,
                R.id.et_number,
                R.id.tv_zip_code_search);


        btnEnviaCadastroProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (estado.equalsIgnoreCase("*Estado")){

                        alertEstado();


                }else {

                    validaCampos();

                }








            }
        });
    }

    private void alertEstado() {

        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulbuilder.setTitle("Escolha o estado");

        builder.setMessage("Você deve escolher o seu estado de origem no formulário");

        //define um botão como positivo
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                alerta.dismiss();

            }
        });

        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();

        }

    private void alertDadosProfissional() {

        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulbuilder.setTitle("Escolha o estado");

        builder.setMessage("Deseja revisar os dados cadastrais antes de enviar o cadastro ?");

        //define um botão como positivo
        builder.setPositiveButton("enviar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                alerta.dismiss();
                enviandoCadastro();


            }
        });

        builder.setNegativeButton("revisar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                alerta.dismiss();

            }
        });


        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();

    }

    private void enviandoCadastro() {

        //LayoutInflater é utilizado para inflar nosso layout em uma view.
        //-pegamos nossa instancia da classe
        LayoutInflater li = getLayoutInflater();

        //inflamos o layout alerta.xml na view
        View view = li.inflate(R.layout.carregando, null);



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == Address.REQUEST_ZIP_CODE_CODE
                && resultCode == RESULT_OK ){

            etZipCode.setText( data.getStringExtra( Address.ZIP_CODE_KEY ) );
        }
    }

    public String getUriZipCode(){
        return "https://viacep.com.br/ws/"+etZipCode.getText()+"/json/";
    }


    public void lockFields( boolean isToLock ){
        util.lockFields( isToLock );
    }


    public void setDataViews(Address address){
        setField( R.id.et_street, address.getLogradouro() );
        setField( R.id.et_complement, address.getComplemento() );
        setField( R.id.et_neighbor, address.getBairro() );
        setField( R.id.et_city, address.getLocalidade() );
     //   setSpinner( R.id.sp_state, R.array.states, address.getUf() );
    }

    private void setField( int id, String data ){

        ((EditText) findViewById(id)).setText( data );

    }
/*
    private void setSpinner( int id, int arrayId, String data ){
        String[] itens = getResources().getStringArray(arrayId);

        for( int i = 0; i < itens.length; i++ ){

            if( itens[i].endsWith( "("+data+")" ) ){
                ((Spinner) findViewById(id)).setSelection( i );
                return;
            }
        }
        ((Spinner) findViewById(id)).setSelection( 0 );
    }
*/


    public void searchZipCode( View view ){

        Intent intent = new Intent( this, ZipCodeSearchActivity.class );
        startActivityForResult(intent, Address.REQUEST_ZIP_CODE_CODE);
    }

    private void validaCampos() {

        String nome = edtNomeProfissional.getText().toString();
        String celular = edtCelularProfissional.getText().toString();
        String rua = edtRuaProfissional.getText().toString();
        String numero = edtNumeroProfissional.getText().toString();
        String complemento = edtComplementoProfissional.getText().toString();
        String bairro = edtBairroProfissional.getText().toString();
        String cidade = edtCidadeProfissional.getText().toString();
        String cep = etZipCode.getText().toString();



        boolean ok = true;

        if (isCampoVazio(nome)) {

            edtNomeProfissional.setError("dados incorretos");
            edtNomeProfissional.requestFocus();

            ok = false;
        }



        if (isCampoVazio(celular) ||
                celular.length() < 14) {

            edtCelularProfissional.setError("dados incorretos");
            edtCelularProfissional.requestFocus();
            ok = false;
        }

        if (isCampoVazio(cep)) {

            etZipCode.setError("dados incorretos");
            etZipCode.requestFocus();
            ok = false;
        }




        if (isCampoVazio(rua)) {

            edtRuaProfissional.setError("dados incorretos");
            edtRuaProfissional.requestFocus();
            ok = false;
        }

        if (isCampoVazio(numero)) {

            edtNumeroProfissional.setError("dados incorretos");
            edtNumeroProfissional.requestFocus();
            ok = false;
        }

        if (isCampoVazio(bairro)) {

            edtBairroProfissional.setError("dados incorretos");
            edtBairroProfissional.requestFocus();
            ok = false;
        }

        if (isCampoVazio(cidade)) {

            edtCidadeProfissional.setError("dados incorretos");
            edtCidadeProfissional.requestFocus();
            ok = false;
        }

  /*
        if (isCampoVazio(estado)) {

            edtEstadoProfissional.setError("dados incorretos");
            edtEstadoProfissional.requestFocus();
            ok = false;
        }
  */

        if (isCampoVazio(complemento)) {

            edtComplementoProfissional.setError("dados incorretos");
            edtComplementoProfissional.requestFocus();
            ok = false;
        }


        if (ok) {

            alertDadosProfissional();

         //   if (estado.)


            criaCadastro(nome,celular,bairro,cep,numero,cidade,estado);




        }

    }

    private void criaCadastro(String nome, String celular, String bairro, String cep,
                                             String numero, String cidade, String estado) {

        final DadosGeraisProfissionalFisica  dadosGerais;
        dadosGerais = new DadosGeraisProfissionalFisica();

        dadosGerais.setNome(nome);
        dadosGerais.setCelular(celular);
        dadosGerais.setBairro(bairro);
        dadosGerais.setCep(cep);
        dadosGerais.setCidade(cidade);
        dadosGerais.setNumero(numero);
        dadosGerais.setEstado(estado);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();


        String uid = mUser.getUid();




                    mFirestore.collection("profissional")
                            .document(estado)
                            .collection(cidade)
                            .document(uid)
                    .set(dadosGerais)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            alerta.dismiss();

                            startActivity(new Intent(SignUpActivity.this,TelaProfissonal.class));
                            finish();


                        Toast.makeText(SignUpActivity.this,
                                      "dados gravados com sucesso",Toast.LENGTH_SHORT).show();


                        }
                    }).addOnFailureListener(new OnFailureListener() {

                        @Override
                        public void onFailure(@NonNull Exception e) {


                            Toast.makeText(SignUpActivity.this,
                                    "falha ao gravar os dados",Toast.LENGTH_SHORT).show();


                        }
                    });


                    mFirestore.collection("profissional")
                            .document(estado)
                            .collection(cidade)
                            .document(uid)
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {

                                    DadosGeraisProfissionalFisica dados =
                                              documentSnapshot.toObject(DadosGeraisProfissionalFisica.class);

                                    Toast.makeText(SignUpActivity.this,dados.getCidade()
                                                           ,Toast.LENGTH_LONG).show();

                                }
                            });




    }


    private static boolean isCampoVazio(String valor) {


        boolean resultado = (TextUtils.isEmpty(valor) ||
                valor.trim().isEmpty());

        return resultado;


    }




}
