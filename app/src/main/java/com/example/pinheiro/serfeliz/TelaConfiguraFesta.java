package com.example.pinheiro.serfeliz;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pinheiro.serfeliz.bancointerno.BancoInternoResumo;
import com.example.pinheiro.serfeliz.bancointerno.PostContract;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import pojos.Cliente;
import pojos.Festa;
import util.MaskMoeda;

public class TelaConfiguraFesta extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private SlideAdapter slideAdapter;
    private int mCurrentPage;
    RelativeLayout relativeConfigFesta;

    private TextView[] mDots;

    protected AlertDialog alerta;
    ImageButton next ;
    ImageButton back;
    Button btnConfiguraFesta;

    FirebaseFirestore mFirestore;

    TextView txtEscolheData,txtConfiguraFestaTitulo,txtConfiguraFestaDescricao;
    EditText edtOrcamento;
    Calendar mCurrentDate;

    private String nome;

    TextView txtConfiguraFesta;
    static String dia,dataFesta,orcamento,tipo;

    FirebaseAuth mAuth;
    CardView cardConfiguraFesta;
    static FirebaseUser userCliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_configura_festa);

     String valor =   addMask("3400");

        // link fs mascara de texto;
        // https://pt.stackoverflow.com/questions/277985/mascara-para-dinheiro-em-android

        Log.d("valorformatado",valor);

        pegaOrcamentoFesta();

        next         = (ImageButton) findViewById(R.id.btn_Next_Button);
        back         = (ImageButton) findViewById(R.id.btn_Back_Button);
        btnConfiguraFesta = (Button)findViewById(R.id.btn_Configura_Festa);
        cardConfiguraFesta = (CardView)findViewById(R.id.card_Configura_Festa);
        txtConfiguraFesta = (TextView)findViewById(R.id.txt_Tipo_Festa_Configura_Festa);
        txtEscolheData = (TextView)findViewById(R.id.txt_Configura_Festa_Escolhe_Data);
        edtOrcamento = (EditText)findViewById(R.id.edt_Configura_Festa_Orcamento);
        txtConfiguraFestaTitulo = (TextView)findViewById(R.id.txt_Configura_Festa_Titulo);
        txtConfiguraFestaDescricao = (TextView)findViewById(R.id.txt_Configura_Festa_Descricao);


        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mCurrentDate = Calendar.getInstance();


        Locale mLocale = new Locale("pt", "BR");
        edtOrcamento.addTextChangedListener(new MaskMoeda(edtOrcamento,mLocale));



        txtConfiguraFesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogTipoFesta();

            }
        });

        btnConfiguraFesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                orcamento = edtOrcamento.getText().toString();

                if(orcamento != null && dataFesta != null && tipo != null){
                    Toast.makeText(TelaConfiguraFesta.this,orcamento,Toast.LENGTH_LONG).show();


                MyTelaConfiguraFesta myTelaConfiguraFesta = new MyTelaConfiguraFesta();

                myTelaConfiguraFesta.execute();

            } else{

                Toast.makeText(TelaConfiguraFesta.this,"Por Favor preencha todos os campos",Toast.LENGTH_LONG).show();
            }



            }
        });



        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                mCurrentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                mCurrentDate.set(Calendar.MONTH, monthOfYear);
                mCurrentDate.set(Calendar.YEAR, year);

                verificaDiaDaSemana(mCurrentDate);

            }

        };



        txtEscolheData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(TelaConfiguraFesta.this, date, mCurrentDate
                        .get(Calendar.YEAR), mCurrentDate.get(Calendar.MONTH),
                        mCurrentDate.get(Calendar.DAY_OF_MONTH)).show();


            }
        });


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {

            Date ontem = sdf.parse("27/10/2018");
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(ontem);
            int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);
            String diaaDia = String.valueOf(diaDaSemana);
            //  Toast.makeText(getContext(), diaaDia, Toast.LENGTH_SHORT).show();

        } catch (ParseException e) {
            e.printStackTrace();
        }




        //tipoFesta = new Festa();

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        slideAdapter =  new SlideAdapter(this);
        mSlideViewPager.setAdapter(slideAdapter);



        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);


        next.setOnClickListener(new View.OnClickListener() {


           @Override
           public void onClick(View view) {


              mSlideViewPager.setCurrentItem(mCurrentPage + 1);



           }
       });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });


    }


    private void configuraTipoFesta(Festa tipo) {


        Toast.makeText(TelaConfiguraFesta.this, "estou no configura tipo festa", Toast.LENGTH_SHORT).show();

        mAuth = FirebaseAuth.getInstance();

            FirebaseUser user =
                    mAuth.getCurrentUser();


            String mUid =
                user.getUid();



        mFirestore.collection("plataforma")
                .document("cliente")
                .collection(mUid)
                .document("tipo festa")
                .set(tipo)

                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(TelaConfiguraFesta.this, "tipo festa criado", Toast.LENGTH_SHORT).show();


                       // startActivity(new Intent(TelaConfiguraFesta.this,TelaCliente.class));
                     //   finish();
                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(TelaConfiguraFesta.this,"ocorreu um erro desconhecido",
                        Toast.LENGTH_SHORT).show();

            }
        });


    }


    public void addDotsIndicator(int position){

        mDots = new TextView[4];
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

                                           // estou na primeira posição

                                           next.setEnabled(true);
                                           back.setEnabled(false);
                                           back.setVisibility(View.INVISIBLE);
                                           next.setVisibility(View.VISIBLE);
                                           btnConfiguraFesta.setVisibility(View.INVISIBLE);
                                           cardConfiguraFesta.setVisibility(View.INVISIBLE);


                                       }else if (i == mDots.length -1){

                                           //estou na segunda posição
                                           next.setEnabled(true);
                                           back.setEnabled(true);
                                           back.setVisibility(View.VISIBLE);
                                           next.setVisibility(View.INVISIBLE);

                                           btnConfiguraFesta.setVisibility(View.VISIBLE);
                                           cardConfiguraFesta.setVisibility(View.VISIBLE);
                                           txtConfiguraFestaTitulo.setVisibility(View.VISIBLE);
                                           txtConfiguraFestaDescricao.setVisibility(View.VISIBLE);


                                       }else if (i == mDots.length -2){

                                           //estou na terceira posição
                                           next.setEnabled(true);
                                           back.setEnabled(true);
                                           back.setVisibility(View.VISIBLE);
                                           next.setVisibility(View.VISIBLE);
                                           btnConfiguraFesta.setVisibility(View.INVISIBLE);
                                           cardConfiguraFesta.setVisibility(View.INVISIBLE);
                                           txtConfiguraFestaTitulo.setVisibility(View.INVISIBLE);
                                           txtConfiguraFestaDescricao.setVisibility(View.INVISIBLE);



                                       }


                                       else
                                           {

                                           //estou na quarta posição

                                           next.setEnabled(true);
                                           back.setEnabled(true);
                                           back.setVisibility(View.VISIBLE);
                                           next.setVisibility(View.VISIBLE);
                                           btnConfiguraFesta.setVisibility(View.INVISIBLE);
                                               cardConfiguraFesta.setVisibility(View.INVISIBLE);
                                               txtConfiguraFestaTitulo.setVisibility(View.INVISIBLE);
                                               txtConfiguraFestaDescricao.setVisibility(View.INVISIBLE);

                                       }
                                   }

                                   @Override
                                   public void onPageScrollStateChanged(int i) {

                                   }
                               };



    private void dialogTipoFesta() {

        //Lista de itens
        ArrayList<String> itens = new ArrayList<String>();

        itens.add("ANIVERSÁRIO");
        itens.add("CASAMENTO");
        itens.add("DEBUTANTE");

        //adapter utilizando um layout customizado (TextView)
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.item_tipo_festa, itens);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Escolha o tipo de festa:");
        //define o diálogo como uma lista, passa o adapter.
        builder.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                String posicaoHorario = String.valueOf(arg1);

                if (posicaoHorario.equalsIgnoreCase("0")){

                tipo = "Anivesário";
                txtConfiguraFesta.setTextColor(Color.parseColor("#DE000000"));
                txtConfiguraFesta.setText(tipo);


                }else if (posicaoHorario.equalsIgnoreCase("1")){

                  //  horario = "De 13:00 ás 16:00 hrs";
                }else if (posicaoHorario.equalsIgnoreCase("2")){

                   // horario = "De 16:00 ás 18:00 hrss";
                }

                alerta.dismiss();
            }
        });

        alerta = builder.create();
        alerta.show();
    }


    public void verificaDiaDaSemana(Calendar cal) {

        SimpleDateFormat sdfa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
         dia = sdf.format(cal.getTime());


        long data =  cal.getTimeInMillis();
        String valor = String.valueOf(data);
        Date calll = cal.getTime();

        Date dataAtual = new Date();

        dataAtual.getTime();

        if (calll.compareTo (dataAtual) < 0) {

            alertDataAntiga();

        } else {


            txtEscolheData.setTextColor(Color.parseColor("#DE000000"));
            txtEscolheData.setText(dia);
            dataFesta = dia;


        }

    }



    public void alertDataAntiga() {

        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);

        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Quase lá!");
        builder.setMessage("Por favor selecione uma data superior á data atual");


        //define um botão como positivo
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                txtEscolheData.setText("Informe a data do evento");

                alerta.dismiss();

            }
        });

        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();

    }


    public void pegaOrcamentoFesta(){
/*
        DecimalFormat formatoDois = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        formatoDois.setMinimumFractionDigits(2);
        formatoDois.setParseBigDecimal (true);
        String valorProduto = formatoDois.format(300030);
*/
        String money = NumberFormat.getCurrencyInstance().format(4500);

        Log.d("valor",money);


        /*
        BigDecimal valor = new BigDecimal ("1830000");
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        String formatado = nf.format (valor);
          Log.d("valor",formatado);

*/

//O resultado é R$ 12.000.000,12

    }

    public static String addMask(final String textoAFormatar) {
        String formatado = "";
        int i = 0;

        if (textoAFormatar.length() == 1) {
            String mask = "R$ #.##";
            DecimalFormat format = new DecimalFormat("0.00");
            String texto = format.format(textoAFormatar);


            // vamos iterar a mascara, para descobrir quais caracteres vamos adicionar e quando...
            for (char m : mask.toCharArray()) {
                if (m != '#') { // se não for um #, vamos colocar o caracter informado na máscara
                    formatado += m;
                    continue;
                }
                // Senão colocamos o valor que será formatado
                try {
                    formatado += texto.charAt(i);


                } catch (Exception e) {
                    break;
                }
                i++;
            }

        }


        return formatado;
    }



    class MyTelaConfiguraFesta extends AsyncTask<String, String, String> {




        public MyTelaConfiguraFesta(){




        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //    alerta_dialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {

            Cliente cliente = new Cliente();
            userCliente = mAuth.getInstance().getCurrentUser();


            String uidCliente =   userCliente.getUid();
            String emailCliente = userCliente.getEmail();
            String nomeCliente =  userCliente.getDisplayName();
            Uri foto =            userCliente.getPhotoUrl();

             String urlPhoto = String.valueOf(foto);

                mFirestore = FirebaseFirestore.getInstance();

                long timeInMillis = System.currentTimeMillis();
                Calendar cal1 = Calendar.getInstance();
                cal1.setTimeInMillis(timeInMillis);

                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        "dd/MM/yyyy");

                String  dateCadastro = dateFormat.format(cal1.getTime());

                cliente.setNome(nomeCliente);
                cliente.setEmail(emailCliente);
                cliente.setDataCadastro(dateCadastro);
                cliente.setUidCliente(uidCliente);
                cliente.setUrlPhoto(urlPhoto);
                cliente.setDataFesta(dataFesta);
                cliente.setOrcamento(orcamento);
                cliente.setTipoFesta(tipo);


                mFirestore.collection("cliente")
                        .document(uidCliente)
                        .collection("dados gerais")
                        .document("dados")
                        .set(cliente)
                        .addOnSuccessListener( new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {




                                startActivity(new Intent(TelaConfiguraFesta.this,TelaCliente.class));
                                finish();

                                Log.d(

                                        "TAG", "DocumentSnapshot successfully written!");


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.d("TAG","ocorreu um erro por favor tente mais tarde!");

                        e.printStackTrace();

                    }
                });






            return null;
        }


        @Override
        protected void onPostExecute(String result) {

        }
    }










}
