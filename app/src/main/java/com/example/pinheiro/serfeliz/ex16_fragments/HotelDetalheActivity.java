package com.example.pinheiro.serfeliz.ex16_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.pinheiro.serfeliz.R;


public class HotelDetalheActivity extends AppCompatActivity {

    public static final String EXTRA_CONTATO = "hotel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detalhe);

      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Contato contato = (Contato)
                intent.getSerializableExtra(EXTRA_CONTATO);

        FragmentCadastroConvidado fragment =
                FragmentCadastroConvidado.novaInstancia(contato);

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.detalhe, fragment,
                FragmentCadastroConvidado.TAG_DETALHE);
        ft.commit();
    }
}
