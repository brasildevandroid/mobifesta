package com.example.pinheiro.serfeliz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class TelaProfissonal extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_profissional_geral:

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_tela_profissional,new FragmentResumo())
                            .commit();


                    return true;
                case R.id.navigation_profissional_pedidos:

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_tela_profissional,new FragmentPedidosDisponiveis())
                            .commit();


                    return true;
                case R.id.navigation_profissional_clientes:

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_tela_profissional,new FragmentCadastroPendente())
                            .commit();


                    return true;



                case R.id.navigation_profissional_mensagens:

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_tela_profissional,new FragmentCadastroPendente())
                            .commit();


                    return true;

            }


            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_profissonal);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_tela_profissional,new FragmentResumo())
                .commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_profissional);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
