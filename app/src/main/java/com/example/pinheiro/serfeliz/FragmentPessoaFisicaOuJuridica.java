package com.example.pinheiro.serfeliz;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pinheiro.serfeliz.R;


public class FragmentPessoaFisicaOuJuridica extends Fragment {


    Button btnPessoaFisica;
    Button btnPessoaJuridica;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pessoa_fisica_ou_juridica,container,false);


        btnPessoaFisica    =   (Button)   view.findViewById(R.id.btn_Cadastro_Pessoa_Fisica);
        btnPessoaJuridica  =   (Button)   view.findViewById(R.id.btn_Cadastro_Pessoa_Juridica);


        btnPessoaFisica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                FragmentManager fmm = getFragmentManager();
                FragmentTransaction ftt = fmm.beginTransaction();
                ftt.replace(R.id.container_tela_principal_cadastro, new FragmentCadastroPessoaFisica());
                ftt.addToBackStack(null);
                ftt.commit();

                */

            }
        });



        btnPessoaJuridica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                FragmentManager fmm = getFragmentManager();
                FragmentTransaction ftt = fmm.beginTransaction();
                ftt.replace(R.id.container_tela_principal_cadastro, new FragmentCadastroPessoaJuridica());
                ftt.addToBackStack(null);
                ftt.commit();
*/
            }
        });

        return view;


    }
}
