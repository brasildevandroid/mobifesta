package com.example.pinheiro.serfeliz.clientetelaconvidados;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.pinheiro.serfeliz.R;

public class TelaConvidado extends AppCompatActivity {

    FloatingActionButton btnAdicionarConvidado;

    FrameLayout frame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_convidado);

        btnAdicionarConvidado = (FloatingActionButton)findViewById(R.id.btn_Flutuante_NovoConvidado);
        frame = (FrameLayout)findViewById(R.id.container_tela_convidado);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_tela_convidado,new FragmentListaConvidados())
             //   .addToBackStack(null)
                .commit();

        btnAdicionarConvidado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           startActivity(new Intent(TelaConvidado.this,TelaCadastroConvidado.class));



                /*
            frame.setVisibility(View.VISIBLE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_tela_convidado,new FragmentCadastroConvidado())
                        .addToBackStack(null)
                        .commit();

*/





            }
        });
    }
}
