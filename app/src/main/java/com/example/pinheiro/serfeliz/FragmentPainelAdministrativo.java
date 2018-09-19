package com.example.pinheiro.serfeliz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;

import pojos.DadosPessoaisProfissional;
import pojos.Funcionarios;

public class FragmentPainelAdministrativo extends Fragment {

    RelativeLayout relCadastros;

    FirebaseFirestore mFirestore;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_painel_administrativo,container,false);

        relCadastros = (RelativeLayout)view.findViewById(R.id.rel_Cadastros);


        mFirestore = FirebaseFirestore.getInstance();

        FirebaseAuth auth = FirebaseAuth.getInstance();



        relCadastros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "fui clicado", Toast.LENGTH_SHORT).show();


                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                    ft .replace(R.id.container_tela_administrativo,new FragmentCadastrosPendentes())
                    .addToBackStack(null)
                    .commit();

            }
        });


        return view;


    }

    private void setCadastros() {

        mFirestore = FirebaseFirestore.getInstance();


            mFirestore.collection("plataforma")
                    .document("profissional")
                    .collection("pendentes")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {


                            if (task.isSuccessful()) {

                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    DadosGeraisProfissionalFisica dados =
                                                                  document.toObject(DadosGeraisProfissionalFisica.class);

                                        ArrayList listPendentes = new ArrayList();



                                        listPendentes.add(dados);



                                    }

                                }

                            }


                    });





    }
}
