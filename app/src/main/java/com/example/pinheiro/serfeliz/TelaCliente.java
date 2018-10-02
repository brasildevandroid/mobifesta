package com.example.pinheiro.serfeliz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pinheiro.serfeliz.lojavirtual.FragmentLojaVirtual;

public class TelaCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cliente);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerTela,new FragmentLojaVirtual())
                .commit();




    }
}
