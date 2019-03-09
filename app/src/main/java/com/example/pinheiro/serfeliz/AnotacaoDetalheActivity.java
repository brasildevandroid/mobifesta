package com.example.pinheiro.serfeliz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pinheiro.serfeliz.bancointerno.BD;
import com.example.pinheiro.serfeliz.bancointerno.Usuario;
import com.example.pinheiro.serfeliz.clientetelaconvidados.TelaConvidado;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import pojos.Anotacoes;


public class AnotacaoDetalheActivity extends AppCompatActivity {

    public static final String EXTRA_CONTATO = "anotacao";

    CheckBox checkBoxConfirmaConvidado;
    String total;
    static FirebaseUser userCliente;

    FirebaseFirestore mFirestore;
    static FirebaseAuth mAuth;
    String idConvidado;
    Anotacoes anotacao;
    TextView txtTituloAnotacao,txtDetalhesAnotacao;
    ImageView imgEditarAnotacao,imgDeletarAnotacao;

    BD bd;

    List<Anotacoes> totalConfirmados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anotacao_detalhe_activity);

      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        anotacao = (Anotacoes)
                intent.getSerializableExtra(EXTRA_CONTATO);

        txtTituloAnotacao = (TextView)findViewById(R.id.txt_Detalhes_Titulo_Anotacoes);
        txtDetalhesAnotacao = (TextView)findViewById(R.id.txt_Detalhes_Descricao_Anotacoes);

        txtTituloAnotacao.setText(anotacao.getTitulo());
        txtDetalhesAnotacao.setText(anotacao.getDescricao());

        bd = new BD(AnotacaoDetalheActivity.this);



        imgEditarAnotacao = (ImageView)findViewById(R.id.img_Editar_Anotacao);
        imgDeletarAnotacao = (ImageView)findViewById(R.id.img_Deletar_Anotacao);




        imgEditarAnotacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent it = new Intent(AnotacaoDetalheActivity.this,TelaEditarAnotacao.class);

                Anotacoes anotacaoEnviado = (Anotacoes) anotacao;

                it.putExtra(AnotacaoDetalheActivity.EXTRA_CONTATO, anotacaoEnviado);
                startActivity(it);

            }
        });


        imgDeletarAnotacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                userCliente = mAuth.getInstance().getCurrentUser();
                mFirestore = FirebaseFirestore.getInstance();
                String id = anotacao.getId();

                String uid = userCliente.getUid();
                mFirestore.collection("cliente")
                        .document(uid)
                        .collection("anotacao")
                        .document(id)
                        .delete()
                        .addOnSuccessListener( new OnSuccessListener<Void>() {

                            @Override
                            public void onSuccess(Void aVoid) {

                                //  cardCadastroConvidado.setVisibility(View.INVISIBLE);

                                startActivity(new Intent(AnotacaoDetalheActivity.this, TelaMinhasAnotacoes.class));
                                finish();


                                Toast.makeText(AnotacaoDetalheActivity.this,
                                        "anotacao deletado com sucesso!",Toast.LENGTH_LONG).show();

                                Log.d(

                                        "TAG", "DocumentSnapshot successfully written!");
                            }

                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.d("TAG","erro ao gravar os dados");

                        Toast.makeText(AnotacaoDetalheActivity.this,
                                "por favor tente mais tarde,ocorreu um erro desconhecido!",Toast.LENGTH_LONG).show();

                        //cardCadastroConvidado.setVisibility(View.INVISIBLE);

                        e.printStackTrace();

                    }
                });




            }
        });




    }

    class MyTelaDetalheAnotacao extends AsyncTask<String, String, String> {

        Anotacoes novaAnotacao = new Anotacoes();


        public MyTelaDetalheAnotacao(Anotacoes anotacao){


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
            String id = novaAnotacao.getId();
            String uid = userCliente.getUid();

            mFirestore.collection("cliente")
                    .document(uid)
                    .collection("anotacao")
                    .document(id)
                    .update("status","confirmado")
                    .addOnSuccessListener( new OnSuccessListener<Void>() {

                        @Override
                        public void onSuccess(Void aVoid) {

                          //  cardCadastroConvidado.setVisibility(View.INVISIBLE);

                            atualizarListaAnotacao();


                            Toast.makeText(AnotacaoDetalheActivity.this,

                                    "presença do convidado alterada com sucesso!",Toast.LENGTH_LONG).show();

                            Log.d(

                                    "TAG", "DocumentSnapshot successfully written!");
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Log.d("TAG","erro ao gravar os dados");

                    Toast.makeText(AnotacaoDetalheActivity.this,
                            "Não foi possivel alterar á presença  do convidado,por favor tente mais tarde!",Toast.LENGTH_LONG).show();

                    //cardCadastroConvidado.setVisibility(View.INVISIBLE);

                    e.printStackTrace();

                }
            });

            return null;
        }


        @Override
        protected void onPostExecute(String result) {

        }
    }

    public void atualizarListaAnotacao(){


       // totalConfirmados = new ArrayList<Convidado>();

        userCliente = mAuth.getInstance().getCurrentUser();
        mFirestore = FirebaseFirestore.getInstance();
        String uid = userCliente.getUid();
        List<Usuario> list = bd.buscar();
     final   Usuario user =  (Usuario) list.get(0);

        mFirestore.collection("cliente")
                .document(uid)
                .collection("convidados")

                .whereEqualTo("status", "confirmado")


                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d("documentos", document.getId() + " => " + document.getData());

                              //  Convidado convidado = document.toObject(Convidado.class);

                              //  totalConfirmados.add(convidado);
                             //   total = String.valueOf(totalConfirmados.size());


                                user.setConfirmados(total);


                                    bd.atualizar(user);

                            }
                        } else {
                            Log.d("", "Error getting documents: ", task.getException());
                        }
                    }
                });










        startActivity(new Intent(AnotacaoDetalheActivity.this, TelaConvidado.class));
        finish();




    }


}
