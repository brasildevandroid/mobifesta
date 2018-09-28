package com.example.pinheiro.serfeliz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FragmentCadastroPessoaFisica extends Fragment {


    EditText edtCelularPessoaFisica;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cadastro_pessoa_fisica,container,false);


        edtCelularPessoaFisica = (EditText) view.findViewById(R.id.edt_Celular);




            return view;


    }
}
