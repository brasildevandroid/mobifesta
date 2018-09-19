package com.example.pinheiro.serfeliz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TelaAdministrativo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_administrativo);



        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_tela_administrativo,new FragmentPainelAdministrativo())
                .addToBackStack(null)
                .commit();

    }
}
