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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import pojos.Anotacoes;

public class TelaEditarAnotacao extends AppCompatActivity {

    Button btnSalvarAnotacao;
    EditText editarCadastroAnotacaoTitulo;
    EditText editarCadastroAnotacaoDescricao;
    String tituloAnotacao;
    String descricaoAnotacao;
    String numeroAnotacao;

    static FirebaseAuth mAuth;
    static FirebaseUser userCliente;
    FirebaseFirestore mFirestore;
    Anotacoes anotacao;


    private Spinner sp;
   // Convidado convidado;
    public static final String EXTRA_ANOTACAO = "anotacao";
    CardView cardCadastroConvidado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar_anotacao);
        btnSalvarAnotacao = (Button) findViewById(R.id.btn_Salva_Anotacao);



        editarCadastroAnotacaoTitulo = (EditText) findViewById(R.id.editar_Cadastro_Anotacao_Titulo);
        editarCadastroAnotacaoDescricao = (EditText) findViewById(R.id.editar_Cadastro_Anotacao_Descricao);



        cardCadastroConvidado = (CardView) findViewById(R.id.card_Cadastro_Convidado);





        mFirestore = FirebaseFirestore.getInstance();


        Intent intent = getIntent();
        anotacao = (Anotacoes)
                intent.getSerializableExtra(EXTRA_ANOTACAO);


if (anotacao != null){

    numeroAnotacao = anotacao.getId();

    editarCadastroAnotacaoTitulo.setText(anotacao.getTitulo());
    tituloAnotacao = editarCadastroAnotacaoTitulo.getText().toString();

    editarCadastroAnotacaoDescricao.setText(anotacao.getDescricao());
    descricaoAnotacao = editarCadastroAnotacaoDescricao.getText().toString();


}



        btnSalvarAnotacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validaCampos();


            }
        });


    }

    private void validaCampos() {

        tituloAnotacao = editarCadastroAnotacaoTitulo.getText().toString();
        descricaoAnotacao = editarCadastroAnotacaoDescricao.getText().toString();



        boolean ok = true;

        if (isCampoVazio(tituloAnotacao)) {

            Toast.makeText(TelaEditarAnotacao.this, "por favor informe o titulo", Toast.LENGTH_LONG).show();


            ok = false;
        }

        if (isCampoVazio(descricaoAnotacao)) {

            Toast.makeText(TelaEditarAnotacao.this, "por favor informe á descricão", Toast.LENGTH_LONG).show();
            ok = false;
        }

        if (ok) {

            cardCadastroConvidado.setVisibility(View.VISIBLE);

            Anotacoes anotacao = new Anotacoes();
            anotacao.setTitulo(tituloAnotacao);
            anotacao.setDescricao(descricaoAnotacao);




            userCliente = mAuth.getInstance().getCurrentUser();
            mFirestore = FirebaseFirestore.getInstance();


            String uidCliente =   userCliente.getUid();


            Toast.makeText(TelaEditarAnotacao.this, uidCliente, Toast.LENGTH_LONG).show();

            TelaEditarAnotacao.MyTelaCadastroAnotacao myFragmentAnotacao = new TelaEditarAnotacao.MyTelaCadastroAnotacao(anotacao);

            myFragmentAnotacao.execute();


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
                    .update("titulo",tituloAnotacao,
                            "descricao",descricaoAnotacao)

                    .addOnSuccessListener( new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            cardCadastroConvidado.setVisibility(View.INVISIBLE);

                            startActivity(new Intent(TelaEditarAnotacao.this, TelaMinhasAnotacoes.class));
                            finish();


                            Toast.makeText(TelaEditarAnotacao.this,
                                    "anotação editada com sucesso!",Toast.LENGTH_LONG).show();

                            Log.d(

                                    "TAG", "DocumentSnapshot successfully written!");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Log.d("TAG","erro ao gravar os dados");

                    Toast.makeText(TelaEditarAnotacao.this,
                            "Não foi possivel editar á anotação,por favor tente mais tarde!",Toast.LENGTH_LONG).show();

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



