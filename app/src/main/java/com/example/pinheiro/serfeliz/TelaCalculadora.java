package com.example.pinheiro.serfeliz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pinheiro.serfeliz.bancointerno.BD;
import com.example.pinheiro.serfeliz.bancointerno.Usuario;

import java.util.ArrayList;
import java.util.List;

public class TelaCalculadora extends AppCompatActivity {

    ImageView imgCalculadoraMenosAdulto,imgCalculadoraMaisAdulto,imgCalculadoraMenosCrianca,imgCalculadoraMaisCrianca,
            imgMenosBolo,imgMaisBolo,imgMenosDocinhos,imgMaisDocinhos,imgMenosSalgadinhos,
            imgMaisSalgadinhos,imgMenosSanduiches,imgMaisSanduiches,imgMenosRefrigerante,imgMaisRefrigerante,
            imgMenosSuco,imgMaisSuco,imgMenosAgua,imgMaisAgua,imgMenosPratinhos,imgMaisPratinhos,imgMenosCopos,imgMaisCopos,
            imgMenosColheres,imgMaisColheres,imgMenosGarfos,imgMaisGarfos,imgMenosGuardanapos,imgMaisGuardanapos,imgMaisCerveja,imgMenosCerveja;


    TextView txtAdultos,txtCriancas,txtBolo,txtDocinhos,txtSalgadinhos,txtSanduiches,txtRefrigerante,txtSuco,txtAgua,txtPratinhos,
             txtCopos,txtColheres,txtGarfos,txtGuardanapos,txtCerveja;
   static int adultos, criancas,bolo,docinhos,salgadinhos,sanduiches,refrigerante,suco,agua,pratinhos,copos,colheres,garfos,guardanapos,cerveja;

    static  double totalConvidados = 0;
    Button btnCalcular;
    String pegaBolo;
    BD bd;
    Usuario user;
    ItensCalculadora itensCalculadora;
    CheckBox checkRefrigerante;
    String refriNaLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_calculadora);

        itensCalculadora = new ItensCalculadora();

        bd = new BD(this);
        List<Usuario> list = bd.buscar();

        user = (Usuario) list.get(0);

        pegaBolo = user.getCalculadoraBolo();


        String quantAdultos = user.getCalculadoraAdultos();
        String quantCriancas = user.getCalculadoraCriancas();
        String bolos = user.getCalculadoraBolo();
        String docinho = user.getCalculadoraDocinhos();
        String salgadinho = user.getCalculadoraSalgadinhos();
        String sanduiche = user.getCalculadoraSanduiches();
        String refri = user.getCalculadoraRefrigerante();
        final String sucos = user.getCalculadoraSuco();
        String aguas = user.getCalculadoraAgua();
        final String cerva = user.getCalculadoraCerveja();
        final String pratinho = user.getCalculadoraPratinhos();
        final String copo = user.getCalculadoraCopos();
        String colher = user.getCalculadoraColheres();
        String garfo = user.getCalculadoraGarfos();
        String guardanapo = user.getCalculadoraGuardanapos();

        txtAdultos = (TextView) findViewById(R.id.txt_Calculadora_Adulto);
        txtCriancas = (TextView) findViewById(R.id.txt_Calculadora_Criancas);
        txtDocinhos = (TextView) findViewById(R.id.txt_Calculadora_Doces);
        txtRefrigerante = (TextView) findViewById(R.id.txt_Calculadora_Refrigerante);
        txtBolo = (TextView) findViewById(R.id.txt_Calculadora_Bolo);
        txtSalgadinhos = (TextView) findViewById(R.id.txt_Calculadora_Salgadinhos);
        txtSanduiches = (TextView) findViewById(R.id.txt_Calculadora_Sanduiches);
        txtSuco = (TextView) findViewById(R.id.txt_Calculadora_Suco);
        txtAgua = (TextView) findViewById(R.id.txt_Calculadora_Agua);
        txtCerveja = (TextView) findViewById(R.id.txt_Calculadora_Cerveja);
        txtPratinhos = (TextView) findViewById(R.id.txt_Calculadora_Pratinhos);
        txtCopos = (TextView) findViewById(R.id.txt_Calculadora_Copos);
        txtColheres = (TextView) findViewById(R.id.txt_Calculadora_Colheres);
        txtGarfos = (TextView) findViewById(R.id.txt_Calculadora_Garfos);
        txtGuardanapos = (TextView) findViewById(R.id.txt_Calculadora_Guardanapos);

        imgCalculadoraMaisAdulto = (ImageView) findViewById(R.id.img_Calculadora_Mais_Adultos);
        imgCalculadoraMenosAdulto = (ImageView) findViewById(R.id.img_Calculadora_Menos_Adultos);
        imgCalculadoraMaisCrianca = (ImageView) findViewById(R.id.img_Calculadora_Mais_Criancas);
        imgCalculadoraMenosCrianca = (ImageView) findViewById(R.id.img_Calculadora_Menos_Criancas);
        imgMenosBolo = (ImageView) findViewById(R.id.img_Menos_Bolo);
        imgMaisBolo = (ImageView) findViewById(R.id.img_Mais_Bolo);
        imgMenosDocinhos = (ImageView) findViewById(R.id.img_Menos_Docinhos);
        imgMaisDocinhos = (ImageView) findViewById(R.id.img_Mais_Docinhos);
        imgMenosSalgadinhos = (ImageView) findViewById(R.id.img_Menos_Salgadinhos);
        imgMaisSalgadinhos = (ImageView) findViewById(R.id.img_Mais_Salgadinhos);
        imgMenosSanduiches = (ImageView) findViewById(R.id.img_Menos_Sanduiches);
        imgMaisSanduiches = (ImageView) findViewById(R.id.img_Mais_Sanduiches);
        imgMenosRefrigerante = (ImageView) findViewById(R.id.img_Menos_Refrigerante);
        imgMaisRefrigerante = (ImageView) findViewById(R.id.img_Mais_Refrigerante);
        imgMenosCerveja = (ImageView) findViewById(R.id.img_Menos_Cerveja);
        imgMaisCerveja = (ImageView) findViewById(R.id.img_Mais_Cerveja);
        imgMenosSuco = (ImageView) findViewById(R.id.img_Menos_Suco);
        imgMaisSuco = (ImageView) findViewById(R.id.img_Mais_Suco);
        imgMenosAgua = (ImageView) findViewById(R.id.img_Menos_Agua);
        imgMaisAgua = (ImageView) findViewById(R.id.img_Mais_Agua);
        imgMenosPratinhos = (ImageView) findViewById(R.id.img_Menos_Pratinhos);
        imgMaisPratinhos = (ImageView) findViewById(R.id.img_Mais_Pratinhos);
        imgMenosCopos = (ImageView) findViewById(R.id.img_Menos_Copos);
        imgMaisCopos = (ImageView) findViewById(R.id.img_Mais_Copos);
        imgMaisColheres = (ImageView) findViewById(R.id.img_Mais_Colheres);
        imgMenosColheres = (ImageView) findViewById(R.id.img_Menos_Colheres);
        imgMaisGarfos = (ImageView) findViewById(R.id.img_Mais_Garfos);
        imgMenosGarfos = (ImageView) findViewById(R.id.img_Menos_Garfos);
        imgMaisGuardanapos = (ImageView) findViewById(R.id.img_Mais_Guardanapos);
        imgMenosGuardanapos = (ImageView) findViewById(R.id.img_Menos_Guardanapos);
        btnCalcular = (Button) findViewById(R.id.btn_Calcular);


        String conv = user.getConvidadosAdultos();

        adultos = Integer.parseInt(user.getConvidadosAdultos());
        txtAdultos.setText(String.valueOf(adultos));
        criancas = Integer.parseInt(user.getConvidadosCriancas());
        txtCriancas.setText(String.valueOf(criancas));

        txtBolo.setText(bolos);
        txtDocinhos.setText(docinho);
        txtSalgadinhos.setText(salgadinho);
        txtSanduiches.setText(sanduiche);
        txtRefrigerante.setText(refri);
        txtCerveja.setText(cerva);
        txtSuco.setText(sucos);
        txtAgua.setText(aguas);
        txtPratinhos.setText(pratinho);
        txtCopos.setText(copo);
        txtColheres.setText(colher);
        txtGarfos.setText(garfo);
        txtGuardanapos.setText(guardanapo);


        imgCalculadoraMaisAdulto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                adultos++;


                txtAdultos.setText(String.valueOf(adultos));

            }
        });

        imgCalculadoraMenosAdulto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (adultos > 0) {

                    adultos--;

                    txtAdultos.setText(String.valueOf(adultos));

                }


            }
        });


        imgCalculadoraMaisCrianca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                criancas++;

                txtCriancas.setText(String.valueOf(criancas));

            }
        });


        imgCalculadoraMenosCrianca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (criancas > 0) {

                    criancas--;

                    txtCriancas.setText(String.valueOf(criancas));


                }


            }
        });


        // bolo

        imgMenosBolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraBolo();

                if (bolo >= 100) {

                    bolo = Integer.valueOf(c);
                    bolo = bolo - 50;
                    txtBolo.setText(String.valueOf(bolo));
                    user.setCalculadoraBolo(String.valueOf(bolo));
                    bd.atualizar(user);


                    // txtBolo.setText(String.valueOf(c));

                    //   Toast.makeText(TelaCalculadora.this,"á quantidade minima por convidado é 100 gramas",Toast.LENGTH_LONG).show();
                }


                if (bolo < 100) {

                    Toast.makeText(TelaCalculadora.this, "o valor ideal por pessoa é de 100 gramas!", Toast.LENGTH_SHORT).show();

                }

            }
        });


        imgMaisBolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraBolo();

                bolo = Integer.valueOf(c);
                bolo = bolo + 50;
                txtBolo.setText(String.valueOf(bolo));
                user.setCalculadoraBolo(String.valueOf(bolo));

                bd.atualizar(user);

                //  txtBolo.setText(String.valueOf(c));

            }
        });


        //docinhos


        imgMenosDocinhos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraDocinhos();

                docinhos = Integer.valueOf(c);

                if (docinhos >= 8) {

                    docinhos = docinhos - 1;


                    user.setCalculadoraDocinhos(String.valueOf(docinhos));


                    bd.atualizar(user);

                    txtDocinhos.setText(String.valueOf(docinhos));

                    bd.atualizar(user);

                    // txtBolo.setText(String.valueOf(c));

                    //   Toast.makeText(TelaCalculadora.this,"á quantidade minima por convidado é 100 gramas",Toast.LENGTH_LONG).show();
                }


                if (docinhos < 8) {

                    Toast.makeText(TelaCalculadora.this, "o valor ideal por pessoa é de 8 docinhos!", Toast.LENGTH_SHORT).show();

                }


            }
        });


        imgMaisDocinhos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraDocinhos();

                docinhos = Integer.valueOf(c);

                docinhos = docinhos + 1;


                user.setCalculadoraDocinhos(String.valueOf(docinhos));


                bd.atualizar(user);

                txtDocinhos.setText(String.valueOf(docinhos));

                bd.atualizar(user);


            }
        });


        // salgadinhos


        imgMenosSalgadinhos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraSalgadinhos();

                salgadinhos = Integer.valueOf(c);

                if (salgadinhos >= 10) {

                    salgadinhos = salgadinhos - 1;


                    user.setCalculadoraSalgadinhos(String.valueOf(salgadinhos));


                    bd.atualizar(user);

                    txtSalgadinhos.setText(String.valueOf(salgadinhos));

                    bd.atualizar(user);

                    // txtBolo.setText(String.valueOf(c));

                    //   Toast.makeText(TelaCalculadora.this,"á quantidade minima por convidado é 100 gramas",Toast.LENGTH_LONG).show();
                }


                if (salgadinhos < 10) {

                    Toast.makeText(TelaCalculadora.this, "o valor ideal por pessoa é de 10 salgadinhos!", Toast.LENGTH_SHORT).show();

                }


            }
        });


        imgMaisSalgadinhos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraSalgadinhos();

                salgadinhos = Integer.valueOf(c);
                salgadinhos = salgadinhos + 1;

                user.setCalculadoraSalgadinhos(String.valueOf(salgadinhos));

                txtSalgadinhos.setText(String.valueOf(salgadinhos));
                bd.atualizar(user);


            }
        });


        // sanduiches


        imgMenosSanduiches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraSanduiches();

                sanduiches = Integer.valueOf(c);

                if (sanduiches >= 2) {

                    sanduiches = sanduiches - 1;


                    user.setCalculadoraSanduiches(String.valueOf(sanduiches));


                    bd.atualizar(user);

                    txtSanduiches.setText(String.valueOf(sanduiches));

                    bd.atualizar(user);

                    // txtBolo.setText(String.valueOf(c));

                    //   Toast.makeText(TelaCalculadora.this,"á quantidade minima por convidado é 100 gramas",Toast.LENGTH_LONG).show();
                }


                if (sanduiches < 2) {

                    Toast.makeText(TelaCalculadora.this, "o valor ideal por pessoa é de 2 saduiches!", Toast.LENGTH_SHORT).show();

                }


            }
        });


        imgMaisSanduiches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraSanduiches();

                sanduiches = Integer.valueOf(c);
                sanduiches = sanduiches + 1;

                user.setCalculadoraSanduiches(String.valueOf(sanduiches));


                txtSanduiches.setText(String.valueOf(sanduiches));
                bd.atualizar(user);


            }
        });


        // refrigerante


        imgMenosRefrigerante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraRefrigerante();

                refrigerante = Integer.valueOf(c);

                if (refrigerante >= 600) {

                    refrigerante = refrigerante - 50;


                    user.setCalculadoraRefrigerante(String.valueOf(refrigerante));


                    bd.atualizar(user);

                    txtRefrigerante.setText(String.valueOf(refrigerante));

                    bd.atualizar(user);


                }


                if (salgadinhos < 600) {

                    Toast.makeText(TelaCalculadora.this, "o valor ideal por pessoa é de 600 ml de refrigerante!", Toast.LENGTH_SHORT).show();

                }


            }
        });


        imgMaisRefrigerante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String c = user.getCalculadoraRefrigerante();

                refrigerante = Integer.valueOf(c);
                refrigerante = refrigerante + 50;

                user.setCalculadoraRefrigerante(String.valueOf(refrigerante));


                txtRefrigerante.setText(String.valueOf(refrigerante));
                bd.atualizar(user);


            }
        });


        // suco

        imgMenosSuco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraSuco();

                suco = Integer.valueOf(c);

                if (suco >= 400) {

                    suco = suco - 50;


                    user.setCalculadoraSuco(String.valueOf(suco));


                    bd.atualizar(user);

                    txtSuco.setText(String.valueOf(suco));

                    bd.atualizar(user);


                }


                if (suco < 400) {

                    Toast.makeText(TelaCalculadora.this, "o valor ideal por pessoa é de 400 ml de suco!", Toast.LENGTH_SHORT).show();

                }


            }
        });


        imgMaisSuco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraSuco();

                suco = Integer.valueOf(c);
                suco = suco + 50;

                user.setCalculadoraSuco(String.valueOf(suco));


                txtSuco.setText(String.valueOf(suco));
                bd.atualizar(user);


            }
        });


        // agua


        imgMenosAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraAgua();

                agua = Integer.valueOf(c);

                if (agua >= 200) {

                    agua = agua - 50;


                    user.setCalculadoraAgua(String.valueOf(agua));


                    bd.atualizar(user);

                    txtAgua.setText(String.valueOf(agua));

                    bd.atualizar(user);

                    // txtBolo.setText(String.valueOf(c));

                    //   Toast.makeText(TelaCalculadora.this,"á quantidade minima por convidado é 100 gramas",Toast.LENGTH_LONG).show();
                }


                if (agua < 200) {

                    Toast.makeText(TelaCalculadora.this, "o valor ideal por pessoa é de 200 ml de água!", Toast.LENGTH_SHORT).show();

                }


            }
        });


        imgMaisAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraAgua();

                agua = Integer.valueOf(c);
                agua = agua + 50;

                user.setCalculadoraAgua(String.valueOf(agua));

                txtAgua.setText(String.valueOf(agua));

                bd.atualizar(user);


            }
        });



        imgMenosCerveja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraCerveja();

                cerveja = Integer.valueOf(c);

                if (cerveja >= 800) {

                    cerveja = cerveja - 50;


                    user.setCalculadoraCerveja(String.valueOf(cerveja));


                    bd.atualizar(user);

                    txtCerveja.setText(String.valueOf(cerveja));

                    bd.atualizar(user);

                    // txtBolo.setText(String.valueOf(c));

                    //   Toast.makeText(TelaCalculadora.this,"á quantidade minima por convidado é 100 gramas",Toast.LENGTH_LONG).show();
                }


                if (cerveja < 800) {

                    Toast.makeText(TelaCalculadora.this, "o valor ideal por pessoa é de 800 ml de cerveja!", Toast.LENGTH_SHORT).show();

                }


            }
        });


        imgMaisCerveja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraCerveja();

                cerveja = Integer.valueOf(c);
                cerveja = cerveja + 50;

                user.setCalculadoraCerveja(String.valueOf(cerveja));

                txtCerveja.setText(String.valueOf(cerveja));

                bd.atualizar(user);


            }
        });


        // pratinho

        imgMenosPratinhos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraPratinhos();

                pratinhos = Integer.valueOf(c);

                if (pratinhos >= 2) {

                    pratinhos = pratinhos - 1;


                    user.setCalculadoraPratinhos(String.valueOf(pratinhos));


                    bd.atualizar(user);

                    txtPratinhos.setText(String.valueOf(pratinhos));

                    bd.atualizar(user);

                }


                if (pratinhos < 2) {

                    Toast.makeText(TelaCalculadora.this, "o valor ideal por pessoa é de 2 pratinhos!", Toast.LENGTH_SHORT).show();

                }


            }
        });


        imgMaisPratinhos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String c = user.getCalculadoraPratinhos();

                pratinhos = Integer.valueOf(c);
                pratinhos = pratinhos + 1;

                user.setCalculadoraPratinhos(String.valueOf(pratinhos));


                txtPratinhos.setText(String.valueOf(pratinhos));
                bd.atualizar(user);


            }
        });






        // copos


        imgMenosCopos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraCopos();

                copos = Integer.valueOf(c);

                if (copos >= 4) {

                    copos = copos - 1;


                    user.setCalculadoraCopos(String.valueOf(copos));


                    bd.atualizar(user);

                    txtCopos.setText(String.valueOf(copos));

                    bd.atualizar(user);


                }


                if (copos < 4) {

                    Toast.makeText(TelaCalculadora.this, "o valor ideal por pessoa é de 4 copos!", Toast.LENGTH_SHORT).show();

                }


            }
        });


        imgMaisCopos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String c = user.getCalculadoraCopos();

                copos = Integer.valueOf(c);
                copos = copos + 1;

                user.setCalculadoraCopos(String.valueOf(copos));


                txtCopos.setText(String.valueOf(copos));
                bd.atualizar(user);


            }
        });


        // colheres

        imgMenosColheres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraColheres();

                colheres = Integer.valueOf(c);

                if (colheres >= 2) {

                    colheres = colheres - 1;


                    user.setCalculadoraColheres(String.valueOf(colheres));


                    bd.atualizar(user);

                    txtColheres.setText(String.valueOf(colheres));

                    bd.atualizar(user);

                    // txtBolo.setText(String.valueOf(c));

                    //   Toast.makeText(TelaCalculadora.this,"á quantidade minima por convidado é 100 gramas",Toast.LENGTH_LONG).show();
                }


                if (colheres < 2) {

                    Toast.makeText(TelaCalculadora.this, "o valor ideal por pessoa é de 02 colheres!", Toast.LENGTH_SHORT).show();

                }


            }
        });


        imgMaisColheres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String c = user.getCalculadoraColheres();

                colheres = Integer.valueOf(c);
                colheres = colheres + 1;
                user.setCalculadoraColheres(String.valueOf(colheres));
                txtColheres.setText(String.valueOf(colheres));
                bd.atualizar(user);


            }
        });




        // garfos


        imgMenosGarfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraGarfos();

                garfos = Integer.valueOf(c);

                if (garfos >= 2) {

                    garfos = garfos - 1;


                    user.setCalculadoraGarfos(String.valueOf(garfos));


                    bd.atualizar(user);

                    txtGarfos.setText(String.valueOf(garfos));

                    bd.atualizar(user);

                    // txtBolo.setText(String.valueOf(c));

                    //   Toast.makeText(TelaCalculadora.this,"á quantidade minima por convidado é 100 gramas",Toast.LENGTH_LONG).show();
                }


                if (garfos < 2) {

                    Toast.makeText(TelaCalculadora.this, "o valor ideal por pessoa é de 02 garfos!", Toast.LENGTH_SHORT).show();

                }


            }
        });


        imgMaisGarfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String c = user.getCalculadoraGarfos();

                garfos = Integer.valueOf(c);
                garfos = garfos + 1;

                user.setCalculadoraGarfos(String.valueOf(garfos));


                txtGarfos.setText(String.valueOf(garfos));
                bd.atualizar(user);


            }
        });


        // guardanapos

        imgMenosGuardanapos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraGuardanapos();

                guardanapos = Integer.valueOf(c);

                if (guardanapos >= 10) {

                    guardanapos = guardanapos - 1;


                    user.setCalculadoraGuardanapos(String.valueOf(guardanapos));


                    bd.atualizar(user);

                    txtGuardanapos.setText(String.valueOf(guardanapos));

                    bd.atualizar(user);

                    // txtBolo.setText(String.valueOf(c));

                    //   Toast.makeText(TelaCalculadora.this,"á quantidade minima por convidado é 100 gramas",Toast.LENGTH_LONG).show();
                }


                if (guardanapos < 10) {

                    Toast.makeText(TelaCalculadora.this, "o valor ideal por pessoa é de 10 guardanapos!", Toast.LENGTH_SHORT).show();

                }
            }
        });


        imgMaisGuardanapos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String c = user.getCalculadoraGuardanapos();
                guardanapos = Integer.valueOf(c);
                guardanapos = guardanapos + 1;
                user.setCalculadoraGuardanapos(String.valueOf(guardanapos));
                txtGuardanapos.setText(String.valueOf(guardanapos));
                bd.atualizar(user);


            }
        });



        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                calcularComidaBebida();

            }
        });



    }


            public void calcularComidaBebida() {

                ArrayList<String> listaCalculadora = new ArrayList<>();

                String c = user.getCalculadoraBolo();
                bolo = Integer.valueOf(c);


                double ad = Double.parseDouble(String.valueOf(adultos));
                double az = Double.parseDouble(String.valueOf(criancas));
                double criancasTotal = az / 2;

                totalConvidados = ad + criancasTotal;
                int totalConv = (int) adultos + criancas;

                String totalCon = String.valueOf("Quantidade para " + adultos + " adultos" + " e " + criancas + " criancas:");

                listaCalculadora.add(totalCon);

                if (bolo != 0) {

                    double gramas = bolo * totalConvidados;


                    int kilos = (int) (gramas / 1000);

                    int gramasBolo = (int) gramas % 1000;

                    if (gramasBolo != 0 && kilos == 0) {

                        String saida = String.valueOf(gramasBolo + " gramas de bolo;");

                        bd.atualizar(user);

                        listaCalculadora.add(saida);
                        Log.d("saida", saida);

                    } else if (kilos != 0 && gramasBolo != 0) {

                        String segundaSaida = String.valueOf(kilos + " Kg" + " e " + gramasBolo + " gramas de bolo;");
                        listaCalculadora.add(segundaSaida);
                        bd.atualizar(user);

                        Log.d("saida", segundaSaida);


                    } else if (kilos != 0 && gramasBolo == 0) {

                        String terceiraSaida = String.valueOf(kilos + " Kg de bolo;");
                        listaCalculadora.add(terceiraSaida);
                        bd.atualizar(user);

                        Log.d("saida", terceiraSaida);

                    }


                }

                // obtendo os docinhos salvos no banco de  dados interno
                String recebeDocinhos = user.getCalculadoraDocinhos();
                double docDocinhos = Double.parseDouble(recebeDocinhos);


                if (docDocinhos != 0) {

                    int totalDocinhos = (int) (docDocinhos * totalConvidados);

                    String t = String.valueOf(totalDocinhos);


                    listaCalculadora.add(t + " docinhos;");
                }


                // obtendo os salgadinhos salvos no banco de  dados interno
                String recebeSalgadinhos = user.getCalculadoraSalgadinhos();
                double docSalgadinhos = Double.parseDouble(recebeSalgadinhos);


                if (docSalgadinhos != 0) {

                    int totalSalgadinhos = (int) (docSalgadinhos * totalConvidados);

                    String t = String.valueOf(totalSalgadinhos + " salgadinhos; ");


                    listaCalculadora.add(t);
                }


                // obtendo os sanduiches salvos no banco de  dados interno
                String recebeSanduiches = user.getCalculadoraSanduiches();
                double docSanduiches = Double.parseDouble(recebeSanduiches);


                if (docSanduiches != 0) {

                    int totalSanduiches = (int) (docSanduiches * totalConvidados);

                    String t = String.valueOf(totalSanduiches);


                    listaCalculadora.add(t + " sanduíches;");
                }


                // obtendo os refrigerante salvos no banco de  dados interno
                String recebeRefrigerante = user.getCalculadoraRefrigerante();
                double docRefrigerante = Double.parseDouble(recebeRefrigerante);


                if (docRefrigerante != 0) {


                    double ml = docRefrigerante * totalConvidados;


                    int litros = (int) ml / 1000;

                    int mlRefrigerante = (int) ml % 1000;


                    if (mlRefrigerante != 0 && litros == 0) {

                        String saida = String.valueOf(mlRefrigerante + " ml de refrigerante;");
                        listaCalculadora.add(saida);
                        bd.atualizar(user);


                        Log.d("saida", saida);

                    } else if (litros != 0 && mlRefrigerante != 0) {

                        String segundaSaida = String.valueOf(litros + " litros" + " e " + mlRefrigerante + " ml de refrigerante;");
                        listaCalculadora.add(segundaSaida);
                        bd.atualizar(user);

                        Log.d("saida", segundaSaida);


                    } else if (litros != 0 && mlRefrigerante == 0) {

                        String terceiraSaida = String.valueOf(litros + " litros de refrigerante;");
                        listaCalculadora.add(terceiraSaida);
                        bd.atualizar(user);

                        Log.d("saida", terceiraSaida);

                    }


                }


                // obtendo os refrigerante salvos no banco de  dados interno
                String recebeSuco = user.getCalculadoraSuco();
                double docSuco = Double.parseDouble(recebeSuco);


                if (docSuco != 0) {


                    double ml = docSuco * totalConvidados;


                    int litros = (int) ml / 1000;
                    int mlSuco = (int) ml % 1000;


                    if (mlSuco != 0 && litros == 0) {

                        String saida = String.valueOf(mlSuco + " ml de suco;");
                        listaCalculadora.add(saida);
                        bd.atualizar(user);


                        Log.d("saida", saida);

                    } else if (litros != 0 && mlSuco != 0) {

                        String segundaSaida = String.valueOf(litros + " litros de suco;");
                        listaCalculadora.add(segundaSaida);
                        bd.atualizar(user);

                        Log.d("saida", segundaSaida);


                    } else if (litros != 0 && mlSuco == 0) {

                        String terceiraSaida = String.valueOf(litros + " litros de suco;");
                        listaCalculadora.add(terceiraSaida);
                        bd.atualizar(user);

                        Log.d("saida", terceiraSaida);

                    }


                }


                // obtendo os refrigerante salvos no banco de  dados interno
                String recebeAgua = user.getCalculadoraAgua();
                double docAgua = Double.parseDouble(recebeAgua);


                if (docAgua != 0) {


                    double ml = docAgua * totalConvidados;

                    int litros = (int) ml / 1000;
                    int mlAgua = (int) ml % 1000;


                    if (mlAgua != 0 && litros == 0) {

                        String saida = String.valueOf(mlAgua + " ml de água;");
                        listaCalculadora.add(saida);
                        bd.atualizar(user);


                        Log.d("saida", saida);

                    } else if (litros != 0 && mlAgua != 0) {

                        String segundaSaida = String.valueOf(litros + " litros" + " e " + mlAgua + " ml de água;");
                        listaCalculadora.add(segundaSaida);
                        bd.atualizar(user);

                        Log.d("saida", segundaSaida);


                    } else if (litros != 0 && mlAgua == 0) {

                        String terceiraSaida = String.valueOf(litros + " litros de água;");
                        listaCalculadora.add(terceiraSaida);
                        bd.atualizar(user);

                        Log.d("saida", terceiraSaida);

                    }


                }


                // obtendo os refrigerante salvos no banco de  dados interno
                String recebeCerveja = user.getCalculadoraCerveja();
                double docCerveja = Double.parseDouble(recebeCerveja);


                if (docCerveja != 0) {


                    double ml = docCerveja * adultos;

                    int litros = (int) ml / 1000;
                    int mlCerveja = (int) ml % 1000;


                    if (mlCerveja != 0 && litros == 0) {

                        String saida = String.valueOf(mlCerveja + " ml de cerveja;" );
                        listaCalculadora.add(saida);
                        bd.atualizar(user);



                        Log.d("saida", saida);

                    } else if (litros != 0 && mlCerveja != 0) {

                        String segundaSaida = String.valueOf(litros + " litros" + " e " + mlCerveja + " ml de cerveja;");
                        listaCalculadora.add(segundaSaida);
                        bd.atualizar(user);

                        Log.d("saida", segundaSaida);


                    } else if (litros != 0 && mlCerveja == 0) {

                        String terceiraSaida = String.valueOf(litros + " litros de cerveja;");
                        listaCalculadora.add(terceiraSaida);
                        bd.atualizar(user);

                        Log.d("saida", terceiraSaida);

                    }


                }


                // obtendo os docinhos salvos no banco de  dados interno
                String recebePratinhos = user.getCalculadoraPratinhos();
                double docPratinhos = Double.parseDouble(recebePratinhos);


                if (docPratinhos != 0) {


                    int totalPratinhos = (int) docPratinhos * (adultos + criancas);


                    int resultado;

                    resultado = (totalPratinhos * 20) / 100;



                    int totalMaisPorcentagem = totalPratinhos + resultado;
                    String t = String.valueOf(totalMaisPorcentagem);

                    listaCalculadora.add(t + " pratinhos;");

                }


                // obtendo os docinhos salvos no banco de  dados interno
                String recebeCopos = user.getCalculadoraCopos();
                double docCopos = Double.parseDouble(recebeCopos);


                if (docCopos != 0) {

                    int totalCopos = (int) docCopos * (adultos + criancas);
                    int resultado;
                    resultado = (totalCopos * 20) / 100;
                    int totalMaisPorcentagem = totalCopos + resultado;
                    String t = String.valueOf(totalMaisPorcentagem);

                    listaCalculadora.add(t + " copos;");

                }


                // obtendo os docinhos salvos no banco de  dados interno
                String recebeColheres = user.getCalculadoraColheres();
                double docColheres = Double.parseDouble(recebeColheres);


                if (docColheres != 0) {

                    int totalColheres = (int) docColheres * (adultos + criancas);
                    int resultado;
                    resultado = (totalColheres * 20) / 100;
                    int totalMaisPorcentagem = totalColheres + resultado;
                    String t = String.valueOf(totalMaisPorcentagem);

                    listaCalculadora.add(t + " colheres;");
                }


                // obtendo os garfos salvos no banco de  dados interno
                String recebeGarfos = user.getCalculadoraGarfos();
                double docGarfos = Double.parseDouble(recebeGarfos);


                if (docGarfos != 0) {

                    int totalGarfos = (int) docGarfos * (adultos + criancas);
                    int resultado;
                    resultado = (totalGarfos * 20) / 100;
                    int totalMaisPorcentagem = totalGarfos + resultado;
                    String t = String.valueOf(totalMaisPorcentagem);

                    listaCalculadora.add(t + " garfos;");
                }


                // obtendo os guardanapos salvos no banco de  dados interno
                String recebeGuardanapos = user.getCalculadoraGuardanapos();
                double docGuardanapos = Double.parseDouble(recebeGuardanapos);


                if (docGuardanapos != 0) {

                    int totalGuardanapos = (int) docGuardanapos * (adultos + criancas);
                    int resultado;
                    resultado = (totalGuardanapos * 20) / 100;
                    int totalMaisPorcentagem = totalGuardanapos + resultado;
                    String t = String.valueOf(totalMaisPorcentagem);

                    listaCalculadora.add(t + " guardanapos;");

                }




                Intent it = new Intent(TelaCalculadora.this, CalculadoraDetalheActivity.class);


                it.putStringArrayListExtra("calculadora", listaCalculadora);
                startActivity(it);


            }


        }



