package com.example.pinheiro.serfeliz;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pojos.Festa;

public class TelaConfiguraFesta extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private SlideAdapter slideAdapter;
    private int mCurrentPage;

    private TextView[] mDots;
    Festa  tipoFesta;
    String orcamento;
    String tipo;
    String dataFesta;

    Button next ;
    Button back;
    Button btnCadastroProfissional;

    EditText edtData;
    EditText edtOrcamento;
    Spinner listaSpinner;


    private List<String> tiposDeFestas = new ArrayList<String>();
    private String nome;
    ArrayAdapter<String> adapterFestas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_configura_festa);

        next = (Button) findViewById(R.id.btn_Next_Button);
        back = (Button)findViewById(R.id.btn_Back_Button);
        edtData = (EditText) findViewById(R.id.edt_Data_Festa);
        edtOrcamento = (EditText) findViewById(R.id.edt_Orcamento);
        listaSpinner = (Spinner) findViewById(R.id.spinner);
        btnCadastroProfissional = (Button) findViewById(R.id.btn_Cadastro_Profissional);

        btnCadastroProfissional.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(TelaConfiguraFesta.this, TelaCadastroProfissional.class));
            }
        });

tipoFesta = new Festa();

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        slideAdapter =  new SlideAdapter(this);
        mSlideViewPager.setAdapter(slideAdapter);

        tiposDeFestas.add("escolha uma festa");
        tiposDeFestas.add("Casamento");
        tiposDeFestas.add("Aniversário");
        tiposDeFestas.add("Debutantes");
        tiposDeFestas.add("Bodas");
        tiposDeFestas.add("Chá de Bebê");

        adapterFestas =   new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tiposDeFestas);
        adapterFestas.setDropDownViewResource(android.R.layout.simple_spinner_item);
        listaSpinner.setAdapter(adapterFestas);

        listaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int posicao, long l) {

                tipo = parent.getItemAtPosition(posicao).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);


        next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               mSlideViewPager.setCurrentItem(mCurrentPage + 1);

               dataFesta = edtData.getText().toString();
               orcamento = edtOrcamento.getText().toString();


               if (dataFesta.equals("")){

                   Toast.makeText(TelaConfiguraFesta.this, "informe uma data", Toast.LENGTH_SHORT).show();

               }


               else if (orcamento.equals("")){


                   Toast.makeText(TelaConfiguraFesta.this, "informe o  valor do orçamento", Toast.LENGTH_SHORT).show();

                   /*
               }else if (tipoFesta.equals("")){

                   Toast.makeText(TelaConfiguraFesta.this, "informe o tipo Festa", Toast.LENGTH_SHORT).show();
*/
               }

               else{

                    tipoFesta.setOrcamento(orcamento);
                    tipoFesta.setDataFesta(dataFesta);
                    tipoFesta.setTipoFesta(tipo);

                   Log.d("tipo",tipoFesta.getTipoFesta() +
                    tipoFesta.getOrcamento() + tipoFesta.getDataFesta());
                   configuraFesta(tipoFesta);
               }
           }
       });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });


    }

    private void configuraFesta(Festa nova) {




    }

    public void addDotsIndicator(int position){

        mDots = new TextView[3];
        mDotLayout.removeAllViews();


        for (int i = 0; i < mDots.length;i++){

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimary));

            mDotLayout.addView(mDots[i]);
        }

        if (mDots.length > 0){

            mDots[position].setTextColor(getResources().getColor(R.color.colorAccent));
        }

    }

    ViewPager.OnPageChangeListener viewListener =
                               new ViewPager.OnPageChangeListener() {
                                   @Override
                                   public void onPageScrolled(int i, float v, int i1) {

                                   }

                                   @Override
                                   public void onPageSelected(int i) {

                                       addDotsIndicator(i);
                                       mCurrentPage = i;

                                       if (i == 0){

                                           next.setEnabled(true);
                                           back.setEnabled(false);
                                           back.setVisibility(View.INVISIBLE);

                                           next.setText("Next");
                                           back.setText("");
                                           edtData.setVisibility(View.VISIBLE);
                                           edtOrcamento.setVisibility(View.INVISIBLE);
                                           listaSpinner.setVisibility(View.INVISIBLE);


                                       }else if (i == mDots.length -1){


                                           next.setEnabled(true);
                                           back.setEnabled(true);
                                           back.setVisibility(View.VISIBLE);

                                           next.setText("finalizar");
                                           back.setText("voltar");
                                           edtData.setVisibility(View.INVISIBLE);

                                           edtOrcamento.setVisibility(View.INVISIBLE);
                                           listaSpinner.setVisibility(View.VISIBLE);

                                       }else {

                                           next.setEnabled(true);
                                           back.setEnabled(true);
                                           back.setVisibility(View.VISIBLE);

                                           next.setText("avançar");
                                           back.setText("voltar");
                                           edtData.setVisibility(View.INVISIBLE);
                                           edtOrcamento.setVisibility(View.VISIBLE);
                                           listaSpinner.setVisibility(View.INVISIBLE);

                                       }
                                   }

                                   @Override
                                   public void onPageScrollStateChanged(int i) {

                                   }
                               };

}
