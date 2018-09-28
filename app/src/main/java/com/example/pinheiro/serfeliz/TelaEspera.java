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

import pojos.Cliente;
import pojos.Funcionarios;

public class TelaEspera extends AppCompatActivity {

    FirebaseUser mUser;
    FirebaseFirestore mFirestore;
    String aceitaTermosECondicoes;
    protected AlertDialog alerta;
    private FirebaseAuth mAuth;
    ImageView imgTelaEspera;
    String termosAceitados;
    List<Funcionarios> listaFuncionarios;

    Button btnLerTermos;
    Button btnAceitarTermos;
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


                        carregaUsuario();

                }else {

                    Toast.makeText(TelaEspera.this, "estartou pro login", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(TelaEspera.this,TelaLogin.class));
                    finish();
                }

            }

        }.start();


    }

    private void carregaUsuario() {

        mFirestore = FirebaseFirestore.getInstance();


        FirebaseUser mUser =
                mAuth.getCurrentUser();

        if (mUser !=  null){


            String mUid =
                    mUser.getUid();


            try {




            }catch (Exception e){






            }



            mFirestore.collection("plataforma")
                    .document("cliente")
                    .collection(mUid)
                    .document("dados pessoais")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            Cliente cliente = documentSnapshot.toObject(Cliente.class);

                            String tipoCliente  = cliente.getTipo();

                            if (tipoCliente.equalsIgnoreCase("clienteFesta")){


                            //    startActivity(new Intent(TelaEspera.this,TelaCliente.class));

                                finish();

                            }else {

                                setProfissional();


                            }


                        }
                    });



        }






    }

    private void setProfissional() {

        mFirestore = FirebaseFirestore.getInstance();


        FirebaseUser mUser =
                mAuth.getCurrentUser();







            if (mUser !=  null) {


                String mUid =
                        mUser.getUid();


                mFirestore.collection("plataforma")
                        .document("profissional")
                        .collection(mUid)
                        .document("dados pessoais")
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                                Cliente cliente = documentSnapshot.toObject(Cliente.class);

                                String tipoCliente = cliente.getTipo();

                                if (tipoCliente.equalsIgnoreCase("clienteFesta")) {


                                 //   startActivity(new Intent(TelaEspera.this, TelaCliente.class));

                                    finish();

                                } else {

                                    setProfissional();

                                }


                            }
                        });


            }




        }

    public void setListaFuncionarios(String email) {

        final String verificaEmail = email;

        mFirestore = FirebaseFirestore.getInstance();

        listaFuncionarios = new ArrayList<>();

        if (verificaEmail != null){

            Toast.makeText(TelaEspera.this,verificaEmail,Toast.LENGTH_SHORT).show();


            mFirestore.collection("plataforma")
                    .document("funcionarios")
                    .collection("ativos")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {


                            if (task.isSuccessful()) {

                                for (QueryDocumentSnapshot document : task.getResult()) {

                                        Funcionarios funcionarios = document.toObject(Funcionarios.class);
                                        listaFuncionarios.add(funcionarios);


                                        if (verificaEmail.equalsIgnoreCase(funcionarios.getEmail())) {

                                          //  startActivity(new Intent(TelaEspera.this, TelaAdministrativa.class));
                                            finish();

                                        }else {

                                       //     startActivity(new Intent(TelaEspera.this, TelaCliente.class));
                                            finish();

                                        }


                                    }


                                }
                            }

                    });

        }else {


        }



    }

}
