package com.example.pinheiro.serfeliz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TelaDicasCalculadora extends AppCompatActivity {

    TextView txtDicas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_dicas_calculadora);

        txtDicas = (TextView) findViewById(R.id.txt_Dicas);

        String dicas = "* Se á festa  á ser realizada tiver apenas salgados,considere 5 á mais por pessoa," +
                "ou se tiver apenas mini-sanduíches,considere 02 á mais por pessoa." + "\n" + "\n" + "" +
                "* Se for oferecerido cupkake ou algo semelhante aos convidados,faça á proporção de 1 cupcake para cada 02 docinhos,ou seja diminua da lista" + "\n" +
                "2 docinhos para cada cupcake que você oferecer." + "\n" + "\n" + "" +
                "* Á quantidade do cálculo de descártaveis(pratinhos,colheres,garfos) é referente á cada prato(comida) á ser servido,se for servir mais de um prato,exemplo: strognoff,bolo,torta salgada,deve considerar o valor do cálculo acima para cada prato á ser servido";


        txtDicas.setText(dicas);

    }
}
