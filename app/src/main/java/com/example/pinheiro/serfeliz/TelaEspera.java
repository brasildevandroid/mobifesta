package com.example.pinheiro.serfeliz;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

//import pojos.Cliente;
import pojos.Funcionarios;

public class TelaEspera extends AppCompatActivity {

    FirebaseUser mUser;
    FirebaseFirestore mFirestore;
    String aceitaTermosECondicoes;
    protected AlertDialog alerta;
    private FirebaseAuth mAuth;

    List<Funcionarios> listaFuncionarios;
    ProgressBar progressEspera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_espera);

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        progressEspera = (ProgressBar) findViewById(R.id.progress_Espera);

        new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

                if (mAuth.getCurrentUser() != null){

                     mUser = mAuth.getCurrentUser();

                        carregaUsuario(mUser);

                }else {

                    Toast.makeText(TelaEspera.this, "estartou pro login", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(TelaEspera.this,TelaLogin.class));
                    finish();
                }
            }

        }.start();

    }

    private void carregaUsuario(FirebaseUser user) {

        mFirestore = FirebaseFirestore.getInstance();

        if (user !=  null){

          startActivity(new Intent(TelaEspera.this,TelaCliente.class));

          finish();

        }

    }
}
