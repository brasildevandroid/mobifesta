package com.example.pinheiro.serfeliz;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pinheiro.serfeliz.R;

/**
 *
 */
public class FragmentTermosCondicoes extends android.support.v4.app.Fragment {

Button btnProsseguirCadastro;
Button btnCancelarCadastro;




    public FragmentTermosCondicoes() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_termos_condicoes,container,false);


        btnProsseguirCadastro = (Button) view.findViewById(R.id.btn_Prosseguir_Cadastro);
        btnCancelarCadastro = (Button) view.findViewById(R.id.btn_Cancelar_Cadastro);


        btnProsseguirCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                FragmentManager fmm = getFragmentManager();
                FragmentTransaction ftt = fmm.beginTransaction();
                ftt.replace(R.id.container_tela_principal_cadastro, new FragmentPessoaFisicaOuJuridica());
                ftt.addToBackStack(null);
                ftt.commit();
*/
            }
        });


        btnCancelarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().finish();
            }
        });


        return  view;


    }

}
