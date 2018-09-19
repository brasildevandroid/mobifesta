package com.example.pinheiro.serfeliz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import pojos.Funcionarios;
import profissional.TelaProfissional;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth mAuth;

    FirebaseFirestore mFirestore;


    List<Funcionarios> listaFuncionarios;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        startActivity(new Intent(MainActivity.this, TelaProfissional.class));
        finish();

        FirebaseAuth auth = FirebaseAuth.getInstance();

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {




                String email = mAuth.getCurrentUser().getEmail();
                setListaFuncionarios(email);

            }else {

            startActivity(new Intent(MainActivity.this,TelaLogin.class));

        }
        }




    @Override

    public void onClick(View view) {

        //[btn sair ]
        if (view.getId() == R.id.sign_out) {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                // user is now signed out
                                startActivity(new Intent(MainActivity.this, TelaLogin.class));
                                finish();

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

                                        startActivity(new Intent(MainActivity.this, TelaAdministrativa.class));
                                        finish();

                                    }else {


                                        startActivity(new Intent(MainActivity.this, TelaConfiguraFesta.class));
                                        finish();

                                    }


                                }


                            }
                        }

                    });

        }else {


            startActivity(new Intent(MainActivity.this,TelaLogin.class));


        }



    }

}


