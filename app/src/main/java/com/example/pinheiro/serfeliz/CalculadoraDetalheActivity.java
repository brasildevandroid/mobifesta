package com.example.pinheiro.serfeliz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pinheiro.serfeliz.bancointerno.BD;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Random;

import pojos.Anotacoes;


public class CalculadoraDetalheActivity extends AppCompatActivity {

    public static final String EXTRA_CONTATO = "calculadora";

    CheckBox checkBoxConfirmaConvidado;
    String total;
    static FirebaseUser userCliente;

    FirebaseFirestore mFirestore;
    static FirebaseAuth mAuth;

    ArrayList<String> listaCalculadora;

    Button btnSalvarAnotacao;

    RecyclerView recListaCalculadora;
    AdapterListaCalculadora adapterListaCalculadora;
    private RecyclerView.LayoutManager mLayoutManager;
    TextView txtSalvarListaComidaBebida;
ProgressBar progressCalculadora;
    BD bd;

    TextView txtDetalheDicas,txtSalvarAnotacaoCalculadora,txtTituloDetalheCalculadora,txtQuantidadeBolo,txtDocinhos,
                     txtSalgadinhos,txtSanduiches,txtAgua,txtSuco,txtRefrigerante,txtPratinhos,txtCopos,txtColheres,txtGuardanapos,txtGarfos,txtDicas,txtCerveja;

    String numeroAnotacao;


    String titulo,bolo,docinhos,salgadinhos,sanduiches, refrigerante, suco, agua,
                                    cerveja, pratinhos, copos, colheres, garfos, guardanapos,dicas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora_detalhe_activity);

        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        bd = new BD(CalculadoraDetalheActivity.this);

        //btnSalvarAnotacao = (Button)findViewById(R.id.btn_Salvar_Anotacao_Calculadora);

        //  progressCalculadora = (ProgressBar)findViewById(R.id.progress_Salvar_Lista);
        txtTituloDetalheCalculadora = (TextView)findViewById(R.id.txt_Titulo_Detalhe_Calculadora);
        txtQuantidadeBolo = (TextView)findViewById(R.id.txt_Bolo_Quantidade);
        txtDocinhos = (TextView)findViewById(R.id.txt_Docinhos_Quantidade);
        txtSalgadinhos = (TextView)findViewById(R.id.txt_Salgadinhos_Quantidade);
        txtSanduiches = (TextView)findViewById(R.id.txt_Sanduiches_Quantidade);
        txtAgua = (TextView)findViewById(R.id.txt_Agua_Quantidade);
        txtSuco = (TextView)findViewById(R.id.txt_Suco_Quantidade);
        txtRefrigerante = (TextView)findViewById(R.id.txt_Refrigerante_Quantidade);
        txtCerveja = (TextView)findViewById(R.id.txt_Cerveja_Quantidade);
        txtPratinhos = (TextView)findViewById(R.id.txt_Pratinhos_Quantidade);
        txtCopos = (TextView)findViewById(R.id.txt_Copos_Quantidade);
        txtColheres = (TextView)findViewById(R.id.txt_Colheres_Quantidade);
        txtGuardanapos = (TextView)findViewById(R.id.txt_Guardanapos_Quantidade);
        txtGarfos = (TextView)findViewById(R.id.txt_Garfos_Quantidade);
        txtDetalheDicas = (TextView) findViewById(R.id.txt_Dicas_Anotacao_Calculadora);
        txtSalvarAnotacaoCalculadora = (TextView)findViewById(R.id.txt_Salvar_Anotacao_Calculadora);


        txtDetalheDicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CalculadoraDetalheActivity.this,TelaDicasCalculadora.class));

            }
        });


        Intent intent = getIntent();
        listaCalculadora =
                intent.getStringArrayListExtra(EXTRA_CONTATO);


        titulo = listaCalculadora.get(0);
        bolo = listaCalculadora.get(1);
        docinhos = listaCalculadora.get(2);
        salgadinhos = listaCalculadora.get(3);
        sanduiches = listaCalculadora.get(4);
        refrigerante = listaCalculadora.get(5);
        suco = listaCalculadora.get(6);
        agua = listaCalculadora.get(7);
        cerveja = listaCalculadora.get(8);
        pratinhos  = listaCalculadora.get(9);
        copos = listaCalculadora.get(10);
        colheres = listaCalculadora.get(11);
        garfos = listaCalculadora.get(12);
        guardanapos = listaCalculadora.get(13);
      //  dicas = listaCalculadora.get(14);


        txtTituloDetalheCalculadora.setText(titulo);
        txtQuantidadeBolo.setText(bolo);
        txtDocinhos.setText(docinhos);
        txtSalgadinhos.setText(salgadinhos);
        txtSanduiches.setText(sanduiches);
        txtSuco.setText(suco);
        txtAgua.setText(agua);
        txtRefrigerante.setText(refrigerante);
        txtPratinhos.setText(pratinhos);
        txtCopos.setText(copos);
        txtColheres.setText(colheres);
        txtGarfos.setText(garfos);
        txtGuardanapos.setText(guardanapos);
     //   txtDicas.setText(dicas);
        txtCerveja.setText(cerveja);




        txtSalvarAnotacaoCalculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Random rand = new Random();
                int aleatorio = 500000000;
                int  n = rand.nextInt((aleatorio) + 1);

                numeroAnotacao = String.valueOf(n);

                String descricao = "Comida e Bebida:" +   "\n" +  bolo + "\n" +docinhos + "\n" +salgadinhos + "\n" +sanduiches +  "\n" +suco+ "\n" +agua+ "\n" +refrigerante+"\n" + cerveja +  "\n" +    "Descartáveis:" + "\n"    + "\n" + pratinhos + "\n" +copos + "\n" +colheres+ "\n" +garfos  + "\n" +guardanapos + "\n";


                Anotacoes novaAnotacao = new Anotacoes();

                novaAnotacao.setTitulo(titulo);
                novaAnotacao.setDescricao(descricao);
                novaAnotacao.setId(numeroAnotacao);

                MyTelaCadastroCalculadora myTelaCadastroCalculadora = new MyTelaCadastroCalculadora(novaAnotacao);
                myTelaCadastroCalculadora.execute();

            }
        });




    }


    class MyTelaCadastroCalculadora extends AsyncTask<String, String, String> {


        Anotacoes novaAnotacao = new Anotacoes();


        public MyTelaCadastroCalculadora(Anotacoes anotacao){


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

                         //   cardCadastroConvidado.setVisibility(View.INVISIBLE);

                            //startActivity(new Intent(TelaCadastroAnotacao.this, TelaMinhasAnotacoes.class));
                            //finish();


                            Toast.makeText(CalculadoraDetalheActivity.this,
                                    "lista de comida e bebida foi salvo em sua lista de anotações!",Toast.LENGTH_LONG).show();

                         //   progressCalculadora.setVisibility(View.INVISIBLE);

                            Log.d(

                                    "TAG", "DocumentSnapshot successfully written!");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Log.d("TAG","erro ao gravar os dados");

                    Toast.makeText(CalculadoraDetalheActivity.this,
                            "Não foi possivel salvar o convidado,por favor tente mais tarde!",Toast.LENGTH_LONG).show();

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

}
