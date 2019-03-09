package com.example.pinheiro.serfeliz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

import pojos.Anotacoes;

public class TelaCadastroAnotacao extends AppCompatActivity {

    Button btnSalvarAnotacao;


    EditText edtTituloAnotacao,edtDescricaoAnotacao;


    static final int PICK_CONTACT_REQUEST = 1;
    static FirebaseAuth mAuth;

    String tituloAnotacao;
    String descricaoAnotacao;
    String tipoConvidado;
    static FirebaseUser userCliente;
    String numeroAnotacao;
    FirebaseFirestore mFirestore;


    private Spinner sp;
    Anotacoes anotacoes;
    public static final String EXTRA_CONTATO = "anotacoes";

    CardView cardCadastroConvidado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_anotacao);

        btnSalvarAnotacao = (Button) findViewById(R.id.btn_Salva_Anotacao);
        edtTituloAnotacao = (EditText) findViewById(R.id.edt_Cadastro_Anotacao_Titulo);
        edtDescricaoAnotacao = (EditText) findViewById(R.id.edt_Cadastro_Anotacao_Descricao);


        cardCadastroConvidado = (CardView) findViewById(R.id.card_Cadastro_Convidado);


        mFirestore = FirebaseFirestore.getInstance();


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grupo, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        btnSalvarAnotacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validaCampos();


            }
        });

    }

    private void validaCampos() {


        tituloAnotacao = edtTituloAnotacao.getText().toString();
        descricaoAnotacao = edtDescricaoAnotacao.getText().toString();



        boolean ok = true;

        if (isCampoVazio(tituloAnotacao)) {

            Toast.makeText(TelaCadastroAnotacao.this, "por favor informe um titulo", Toast.LENGTH_LONG).show();


            ok = false;
        }

        if (isCampoVazio(descricaoAnotacao)) {

            Toast.makeText(TelaCadastroAnotacao.this, "por favor descreva sua anotação", Toast.LENGTH_LONG).show();


            ok = false;
        }




        if (ok) {


            Random rand = new Random();
            int aleatorio = 500000000;
            int  n = rand.nextInt((aleatorio) + 1);

           numeroAnotacao = String.valueOf(n);


            cardCadastroConvidado.setVisibility(View.VISIBLE);

            Anotacoes anotacao = new Anotacoes();
            anotacao.setTitulo(tituloAnotacao);
            anotacao.setDescricao(descricaoAnotacao);
            anotacao.setId(numeroAnotacao);

            userCliente = mAuth.getInstance().getCurrentUser();
            mFirestore = FirebaseFirestore.getInstance();


            String uidCliente =   userCliente.getUid();


            Toast.makeText(TelaCadastroAnotacao.this, uidCliente, Toast.LENGTH_LONG).show();

            TelaCadastroAnotacao.MyTelaCadastroAnotacao myFragmentCadastro = new TelaCadastroAnotacao.MyTelaCadastroAnotacao(anotacao);

            myFragmentCadastro.execute();


            }


        }




    private static boolean isCampoVazio(String valor) {


        boolean resultado = (TextUtils.isEmpty(valor) ||
                valor.trim().isEmpty());

        return resultado;


    }




    class MyTelaCadastroAnotacao extends AsyncTask<String, String, String> {

    Anotacoes novaAnotacao = new Anotacoes();


        public MyTelaCadastroAnotacao(Anotacoes anotacao){


        this.novaAnotacao = anotacao;

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
                    .collection("anotacao")
                    .document(numeroAnotacao)
                    .set(novaAnotacao)
                    .addOnSuccessListener( new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            cardCadastroConvidado.setVisibility(View.INVISIBLE);

                            startActivity(new Intent(TelaCadastroAnotacao.this, TelaMinhasAnotacoes.class));
                            finish();




                            Toast.makeText(TelaCadastroAnotacao.this,
                                    "nova anotacao registrado com sucesso!",Toast.LENGTH_LONG).show();

                            Log.d(

                                    "TAG", "DocumentSnapshot successfully written!");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Log.d("TAG","erro ao gravar os dados");

                    Toast.makeText(TelaCadastroAnotacao.this,
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



