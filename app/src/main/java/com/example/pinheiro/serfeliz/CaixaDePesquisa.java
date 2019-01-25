package com.example.pinheiro.serfeliz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class CaixaDePesquisa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Toast.makeText(this,"estou executando",Toast.LENGTH_LONG).show();
    }
}
