package com.example.pinheiro.serfeliz;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

import com.example.pinheiro.serfeliz.bancointerno.BD;
import com.example.pinheiro.serfeliz.bancointerno.Usuario;
import com.example.pinheiro.serfeliz.clientetelaconvidados.TelaConvidado;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Random;

public class TelaEditarMensagem extends AppCompatActivity {

    EditText edtMensagem;
    Button btnSalvarMensagem;

    BD bd;
    String mensagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar_mensagem);

        btnSalvarMensagem = (Button) findViewById(R.id.btn_Salvar_Mensagem);
        edtMensagem = (EditText)findViewById(R.id.edt_Mensagem_Resumo);


        bd = new BD(TelaEditarMensagem.this);



        btnSalvarMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                mensagem  = edtMensagem.getText().toString();

                Toast.makeText(TelaEditarMensagem.this,mensagem,Toast.LENGTH_LONG).show();


                if (mensagem != null){


                    MyTelaCadastroConvidado myTelaCadastroConvidado = new MyTelaCadastroConvidado(mensagem);

                    myTelaCadastroConvidado.execute();

                }

            }
        });



    }


    class MyTelaCadastroConvidado extends AsyncTask<String, String, String> {

            String novaMensagem;


        public MyTelaCadastroConvidado(String mensagem){


        this.novaMensagem = mensagem;


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //    alerta_dialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {

            List<Usuario> list = bd.buscar();

            Usuario user =  (Usuario) list.get(0);

            user.setMensagem(novaMensagem);


            bd.atualizar(user);


            startActivity(new Intent(TelaEditarMensagem.this,TelaCliente.class));
            finish();



            return null;
        }


        @Override
        protected void onPostExecute(String result) {

        }
    }
}



