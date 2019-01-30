package com.example.pinheiro.serfeliz.clientetelaconvidados;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pinheiro.serfeliz.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

public class TelaCadastroConvidado extends AppCompatActivity {

    Button btnSalvarConvidado;
    EditText edtConvidadoCrianca;
    EditText edtConvidadoAdulto;
    EditText edtCadastroConvidadoNome;
    EditText edtCadastroConvidadoCelular;
    ImageView imgListaContatos;
    static final int PICK_CONTACT_REQUEST = 1;
    static FirebaseAuth mAuth;
    String qtdeAcompanhantesCriancas;
    String qtdeAcompanhantesAdultos;
    String nomeConvidado;
    String celularConvidado;
    String tipoConvidado;
    static FirebaseUser userCliente;
    String numeroConvidado;
    FirebaseFirestore mFirestore;


    private Spinner sp;
    Convidado convidado;
    public static final String EXTRA_CONTATO = "convidado";

    CardView cardCadastroConvidado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_convidado);
        btnSalvarConvidado = (Button) findViewById(R.id.btn_Salva_Convidado);

        qtdeAcompanhantesAdultos = "0";
        qtdeAcompanhantesCriancas = "0";

        edtConvidadoCrianca = (EditText) findViewById(R.id.edt_Convidado_Criancas);
        edtConvidadoAdulto = (EditText) findViewById(R.id.edt_Convidado_Adulto);
        edtCadastroConvidadoNome = (EditText) findViewById(R.id.edt_Cadastro_Convidado_Nome);
        edtCadastroConvidadoCelular = (EditText) findViewById(R.id.edt_Cadastro_Convidado_Celular);
        imgListaContatos = (ImageView) findViewById(R.id.img_Lista_Contato);

        cardCadastroConvidado = (CardView) findViewById(R.id.card_Cadastro_Convidado);



        sp = (Spinner) findViewById(R.id.spinner_Tipo_Convidado);

        mFirestore = FirebaseFirestore.getInstance();


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grupo, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(adapter);


        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                String posicao = String.valueOf(position);

                if (posicao.equals("0")) {

                } else if (posicao.equals("1")) {
                    tipoConvidado = "Parente(s)";

                } else if (posicao.equals("2")) {
                    tipoConvidado = "Amigo(s)";

                } else if (posicao.equals("3")) {
                    tipoConvidado = "Vizinho(s)";

                } else if (posicao.equals("4")) {
                    tipoConvidado = "Colega(s)";

                } else if (posicao.equals("5")) {
                    tipoConvidado = "Conhecido(s)";

                } else if (posicao.equals("6")) {
                    tipoConvidado = "Outro(s)";


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        btnSalvarConvidado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validaCampos();


            }
        });

        imgListaContatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 verificaListaContatos();

            }
        });
    }

    private void validaCampos() {


        nomeConvidado = edtCadastroConvidadoNome.getText().toString();
        celularConvidado = edtCadastroConvidadoCelular.getText().toString();


        qtdeAcompanhantesCriancas = edtConvidadoCrianca.getText().toString();
        qtdeAcompanhantesAdultos = edtConvidadoAdulto.getText().toString();


        boolean ok = true;

        if (isCampoVazio(nomeConvidado)) {

            Toast.makeText(TelaCadastroConvidado.this, "por favor informe o nome do convidado", Toast.LENGTH_LONG).show();


            ok = false;
        }

        if (isCampoVazio(tipoConvidado)) {

            Toast.makeText(TelaCadastroConvidado.this, "por favor informe o Grupo do Convidado(a)", Toast.LENGTH_LONG).show();


            ok = false;
        }




        if (ok) {


            Random rand = new Random();
            int aleatorio = 500000000;
            int  n = rand.nextInt((aleatorio) + 1);

           numeroConvidado = String.valueOf(n);



            cardCadastroConvidado.setVisibility(View.VISIBLE);

            Convidado convidado = new Convidado();
            convidado.setNome(nomeConvidado);
            convidado.setCelularConvidado(celularConvidado);
            convidado.setTipoConvidado(tipoConvidado);
            convidado.setAcompanhantesAdultos(qtdeAcompanhantesAdultos);
            convidado.setAcompanhantesCriancas(qtdeAcompanhantesCriancas);
            convidado.setStatus("á confirmar");
            convidado.setId(numeroConvidado);

            userCliente = mAuth.getInstance().getCurrentUser();
            mFirestore = FirebaseFirestore.getInstance();


            String uidCliente =   userCliente.getUid();


            Toast.makeText(TelaCadastroConvidado.this, uidCliente, Toast.LENGTH_LONG).show();

            TelaCadastroConvidado.MyTelaCadastroConvidado myFragmentCadastro = new TelaCadastroConvidado.MyTelaCadastroConvidado(convidado);

            myFragmentCadastro.execute();


            }


        }




    private static boolean isCampoVazio(String valor) {


        boolean resultado = (TextUtils.isEmpty(valor) ||
                valor.trim().isEmpty());

        return resultado;


    }

    public void verificaListaContatos() {

        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        if (requestCode == PICK_CONTACT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // Get the URI that points to the selected contact
                Uri contactUri = data.getData();
                // We only need the NUMBER column, because there will be only one row in the result
                //String[] projection = {Phone.NUMBER};

                // Perform the query on the contact to get the NUMBER column
                // We don't need a selection or sort order (there's only one result for the given URI)
                // CAUTION: The query() method should be called from a separate thread to avoid blocking
                // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
                // Consider using CursorLoader to perform the query.
                Cursor cursor = getContentResolver()
                        .query(contactUri, null, null, null, null);
                cursor.moveToFirst();

                // Retrieve the phone number from the NUMBER column
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                int column2 = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                String number = cursor.getString(column);
                String nome = cursor.getString(column2);

                if (nome != null) {

                    edtCadastroConvidadoNome.setText(nome);

                }

                if (number != null) {

                    edtCadastroConvidadoCelular.setText(number);

                }

                Toast.makeText(TelaCadastroConvidado.this, number + nome, Toast.LENGTH_SHORT).show();


                // Do something with the phone number...
            }
        }
    }



    class MyTelaCadastroConvidado extends AsyncTask<String, String, String> {

    Convidado novoConvidado = new Convidado();


        public MyTelaCadastroConvidado(Convidado convidado){


        this.novoConvidado = convidado;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //    alerta_dialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {


            userCliente = mAuth.getInstance().getCurrentUser();
            mFirestore = FirebaseFirestore.getInstance();


            String uidCliente =   userCliente.getUid();


            mFirestore.collection("cliente")
                    .document(uidCliente)
                    .collection("convidados")
                    .document(numeroConvidado)
                    .set(novoConvidado)
                    .addOnSuccessListener( new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            cardCadastroConvidado.setVisibility(View.INVISIBLE);

                            startActivity(new Intent(TelaCadastroConvidado.this, TelaConvidado.class));
                            finish();




                            Toast.makeText(TelaCadastroConvidado.this,
                                    "novo convidado registrado com sucesso!",Toast.LENGTH_LONG).show();

                            Log.d(

                                    "TAG", "DocumentSnapshot successfully written!");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Log.d("TAG","erro ao gravar os dados");

                    Toast.makeText(TelaCadastroConvidado.this,
                            "Não foi possivel salvar o convidado,por favor tente mais tarde!",Toast.LENGTH_LONG).show();

                    cardCadastroConvidado.setVisibility(View.INVISIBLE);

                    e.printStackTrace();

                }
            });





            return null;
        }


        @Override
        protected void onPostExecute(String result) {

        }
    }
}



