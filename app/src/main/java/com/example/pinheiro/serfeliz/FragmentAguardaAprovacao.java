package com.example.pinheiro.serfeliz;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pinheiro.serfeliz.R;

/**
 *
 */
public class FragmentAguardaAprovacao extends android.support.v4.app.Fragment {

    protected AlertDialog alerta;
    Button btnEnviarCadastroPessoaFisica;

    public FragmentAguardaAprovacao() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_aguarda_aprovacao, container, false);

        return view;

    }



}
