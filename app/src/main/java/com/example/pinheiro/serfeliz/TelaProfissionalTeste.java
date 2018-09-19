package com.example.pinheiro.serfeliz;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class TelaProfissionalTeste extends AppCompatActivity {

    FirebaseFirestore mFirestore;

    ProgressBar progressTelaProfissonal;
    DocumentReference docStatusProfissional ;

    FirebaseAuth mAuth;

    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_profisional_teste);


        progressTelaProfissonal = (ProgressBar) findViewById(R.id.progress_Tela_Profissional);



        mFirestore =
                FirebaseFirestore.getInstance();


        mAuth =
                FirebaseAuth.getInstance();

        if (mAuth != null) {


            checkProfissional();


        }


    }


    private void checkProfissional() {

        mUser = mAuth.getCurrentUser();



        docStatusProfissional  =
                mFirestore.collection("plataforma")
                        .document("profissional")
                        .collection(mAuth.getUid())
                        .document("dados gerais");


                docStatusProfissional.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        DocumentSnapshot document = task.getResult();

                        if (document.exists()) {

                            DadosGeraisProfissionalFisica dadosGerais =
                                           document.toObject(DadosGeraisProfissionalFisica.class);


                            String statusProfissional =
                                     dadosGerais.getStatus();

                            /*
                            Toast.makeText(TelaProfissionalTeste.this, "sim o documente existe" +
                                    dadosGerais.getStatus(), Toast.LENGTH_SHORT).show();
*/
                            setPainelProfissional(statusProfissional);

                        }
                    }
                });



    }

    private void setPainelProfissional(String statusProfissional) {

        String status = statusProfissional;


        if (status.equalsIgnoreCase("ativo")){


            progressTelaProfissonal.setVisibility(View.INVISIBLE);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_tela_profissional,new FragmentPainelProfissional())
                    .addToBackStack(null)
                    .commit();

        }else if (status.equalsIgnoreCase("bloqueado")){


            progressTelaProfissonal.setVisibility(View.INVISIBLE);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_tela_profissional,new FragmentCadastroBloqueado())
                    .addToBackStack(null)
                    .commit();



        }else if (status.equalsIgnoreCase("pendente")){

            progressTelaProfissonal.setVisibility(View.INVISIBLE);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_tela_profissional,new FragmentCadastroPendente())
                    .addToBackStack(null)
                    .commit();


        }

    }

    public void onPause(){

        super.onPause();


      //  checkProfissional();


    }
}
