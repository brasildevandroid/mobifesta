package com.example.pinheiro.serfeliz.barradepesquisa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pinheiro.serfeliz.R;

public class SubActivity extends Activity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        setContentView(R.layout.sub_activity);
        super.onCreate(savedInstanceState);
        tv =(TextView)findViewById(R.id.tv);
        Intent intent = getIntent();
        String nome = intent.getExtras().getString("nome");
        String celular = intent.getExtras().getString("celular");

        tv.setText(" "+ nome+ " " + " "+ celular  +" " );
    }
}
