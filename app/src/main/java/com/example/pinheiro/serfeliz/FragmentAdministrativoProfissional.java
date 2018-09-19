package com.example.pinheiro.serfeliz;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAdministrativoProfissional extends android.support.v4.app.Fragment {


    TextView txtCadastrosPendentes;



    public FragmentAdministrativoProfissional() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_administrativo_profissional,container,false);



        txtCadastrosPendentes  = (TextView)view.findViewById(R.id.txt_Cadastros_Pendentes);


        // Inflate the layout for this fragment

        txtCadastrosPendentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                ft .replace(R.id.container_tela_administrativa,new FragmentCadastrosPendentes())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

}
