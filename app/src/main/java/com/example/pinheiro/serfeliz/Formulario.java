package com.example.pinheiro.serfeliz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Formulario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
    }

    public void signUpActivity(View view){

        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity( intent );
    }
}
