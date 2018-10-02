package com.example.pinheiro.serfeliz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TelaCadastroProfissional extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_profissional);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_tela_cadastro_profissional,new FragmentResumo())
                .addToBackStack(null)
                .commit();

    }
}
