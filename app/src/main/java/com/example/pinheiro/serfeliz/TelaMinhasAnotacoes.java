package com.example.pinheiro.serfeliz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

public class TelaMinhasAnotacoes extends AppCompatActivity {

    FloatingActionButton btnAdicionarAnotacoes;


    FrameLayout frame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_minhas_anotacoes);

        btnAdicionarAnotacoes = (FloatingActionButton)findViewById(R.id.btn_Flutuante_Nova_Anotacao);
        frame = (FrameLayout)findViewById(R.id.container_tela_anotacoes);




        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_tela_anotacoes,new FragmentListaMinhasAnotacoes())
             //   .addToBackStack(null)
                .commit();

        btnAdicionarAnotacoes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

           startActivity(new Intent(TelaMinhasAnotacoes.this,TelaCadastroAnotacao.class));

            }
        });
    }
}
