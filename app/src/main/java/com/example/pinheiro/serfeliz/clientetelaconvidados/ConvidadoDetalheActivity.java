package com.example.pinheiro.serfeliz.clientetelaconvidados;

import android.content.Intent;
import android.net.Uri;
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

import com.example.pinheiro.serfeliz.R;
import com.example.pinheiro.serfeliz.bancointerno.BD;
import com.example.pinheiro.serfeliz.bancointerno.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ConvidadoDetalheActivity extends AppCompatActivity {

    public static final String EXTRA_CONTATO = "convidado";

    CheckBox checkBoxConfirmaConvidado;
    String total;
    static FirebaseUser userCliente;

    FirebaseFirestore mFirestore;
    static FirebaseAuth mAuth;
String idConvidado;
Convidado convidado;
TextView txtPresencaConvidado,txtNomeConvidado,txtCelularConvidado,txtStatusConvidado,txtQtdeAcompanhantesAdultos,txtQtdeAcompanhantesCriancas,txtGrauParentesco;
ImageView imgEditarConvidado,imgDeletarConvidado;
ImageView imgLigar,imgMensagem;
    BD bd;
    TextView txtFazerLigacaoConvidado,txtMensagemConvidado;

    List<Convidado> totalConfirmados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_convidado);

        imgEditarConvidado = (ImageView)findViewById(R.id.img_Editar_Convidado);
        imgDeletarConvidado = (ImageView)findViewById(R.id.img_Deletar_Convidado);


        Intent intent = getIntent();
        convidado = (Convidado)
                intent.getSerializableExtra(EXTRA_CONTATO);



        txtNomeConvidado = (TextView)findViewById(R.id.txt_Detalhe_Nome_Convidado);
        txtCelularConvidado = (TextView)findViewById(R.id.txt_Celular_Convidado);
        txtPresencaConvidado = (TextView)findViewById(R.id.txt_Presenca_Convidado);
        txtStatusConvidado = (TextView)findViewById(R.id.txt_Status_Convidado);

        bd = new BD(ConvidadoDetalheActivity.this);

        txtQtdeAcompanhantesAdultos = (TextView)findViewById(R.id.txt_Acompanhantes_Adultos);
        txtQtdeAcompanhantesCriancas = (TextView)findViewById(R.id.txt_Acompanhantes_Criancas);
        txtGrauParentesco = (TextView)findViewById(R.id.txt_Grau_Parentesco_Convidado);
        checkBoxConfirmaConvidado = (CheckBox)findViewById(R.id.checkBox_Confirmar_Convidado);

        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtFazerLigacaoConvidado = (TextView)findViewById(R.id.txt_Fazer_Ligacao_Convidado);
        txtMensagemConvidado = (TextView)findViewById(R.id.txt_Mensagem_Convidado);





        txtFazerLigacaoConvidado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String telefone = convidado.getCelularConvidado();

                Uri uri = Uri.parse("tel:"+ telefone);
                Intent intent = new Intent(Intent.ACTION_DIAL,uri);

                startActivity(intent);

            }
        });



        txtMensagemConvidado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String telefone = convidado.getCelularConvidado() ;



                Uri uri = Uri.parse("tel:"+telefone);



                Intent sendIntent = new Intent("android.intent.action.MAIN");
                sendIntent.putExtra("jid", "55" + (telefone) + "@s.whatsapp.net");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "você vem na minha festa né ?");
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setPackage("com.whatsapp");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        });



        imgEditarConvidado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent it = new Intent(ConvidadoDetalheActivity.this,TelaEditarConvidado.class);

                Convidado convidadoEnviado = (Convidado) convidado;

                it.putExtra(ConvidadoDetalheActivity.EXTRA_CONTATO, convidadoEnviado);
                startActivity(it);

            }
        });


        imgDeletarConvidado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                userCliente = mAuth.getInstance().getCurrentUser();
                mFirestore = FirebaseFirestore.getInstance();
                String id = convidado.getId();

                String uid = userCliente.getUid();
                mFirestore.collection("cliente")
                        .document(uid)
                        .collection("convidados")
                        .document(id)
                        .delete()
                        .addOnSuccessListener( new OnSuccessListener<Void>() {

                            @Override
                            public void onSuccess(Void aVoid) {

                                //  cardCadastroConvidado.setVisibility(View.INVISIBLE);

                                startActivity(new Intent(ConvidadoDetalheActivity.this, TelaConvidado.class));
                                finish();


                                Toast.makeText(ConvidadoDetalheActivity.this,
                                        "convidado deletado com sucesso!",Toast.LENGTH_LONG).show();

                                Log.d(

                                        "TAG", "DocumentSnapshot successfully written!");
                            }

                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.d("TAG","erro ao gravar os dados");

                        Toast.makeText(ConvidadoDetalheActivity.this,
                                "por favor tente mais tarde,ocorreu um erro desconhecido!",Toast.LENGTH_LONG).show();

                        //cardCadastroConvidado.setVisibility(View.INVISIBLE);

                        e.printStackTrace();

                    }
                });




            }
        });

        txtNomeConvidado.setText(convidado.getNome());
        txtCelularConvidado.setText(convidado.getCelularConvidado());
        txtQtdeAcompanhantesAdultos.setText("( " +convidado.getQtdeAcompanhantesAdultos() + " )" + " Acompanhantes Adultos");
        txtQtdeAcompanhantesCriancas.setText("( " +convidado.getQtdeAcompanhantesCriancas() + " )" + " Acompanhantes Crianças");
        txtGrauParentesco.setText(convidado.getTipoConvidado());
        txtPresencaConvidado.setText(convidado.getStatus());
        txtCelularConvidado.setText(convidado.getCelularConvidado());

        Log.d("detalheConvidado",">>>>>> " + convidado);

        idConvidado  = convidado.getId();

       // checkBoxConfirmaConvidado = (CheckBox)findViewById(R.id.checkBox_Confirmar_Convidado);


        if (convidado.getStatus().equalsIgnoreCase("á confirmar")){

            checkBoxConfirmaConvidado.setVisibility(View.VISIBLE);

        }



        checkBoxConfirmaConvidado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                MyTelaDetalheConvidado myTelaDetalheConvidado = new MyTelaDetalheConvidado(convidado);
                myTelaDetalheConvidado.execute();


            }
        });



        /*
        FragmentCadastroConvidado fragment =
                FragmentCadastroConvidado.novaInstancia(convidado);
*/

        /*
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.detalhe, fragment,
                FragmentCadastroConvidado.TAG_DETALHE);
        ft.commit();
        */


    }

    class MyTelaDetalheConvidado extends AsyncTask<String, String, String> {

        Convidado novoConvidado = new Convidado();


        public MyTelaDetalheConvidado(Convidado convidado){


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
            String id = novoConvidado.getId();
            String uid = userCliente.getUid();

            mFirestore.collection("cliente")
                    .document(uid)
                    .collection("convidados")
                    .document(id)
                    .update("status","confirmado")
                    .addOnSuccessListener( new OnSuccessListener<Void>() {

                        @Override
                        public void onSuccess(Void aVoid) {

                          //  cardCadastroConvidado.setVisibility(View.INVISIBLE);

                            atualizarListaConvidados();


                            Toast.makeText(ConvidadoDetalheActivity.this,

                                    "presença do convidado alterada com sucesso!",Toast.LENGTH_LONG).show();

                            Log.d(

                                    "TAG", "DocumentSnapshot successfully written!");
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Log.d("TAG","erro ao gravar os dados");

                    Toast.makeText(ConvidadoDetalheActivity.this,
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

    public void atualizarListaConvidados(){


        totalConfirmados = new ArrayList<Convidado>();

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

                                Convidado convidado = document.toObject(Convidado.class);

                                totalConfirmados.add(convidado);
                                total = String.valueOf(totalConfirmados.size());


                                user.setConfirmados(total);


                                    bd.atualizar(user);

                            }
                        } else {
                            Log.d("", "Error getting documents: ", task.getException());
                        }
                    }
                });


        startActivity(new Intent(ConvidadoDetalheActivity.this, TelaConvidado.class));
        finish();




    }


}
