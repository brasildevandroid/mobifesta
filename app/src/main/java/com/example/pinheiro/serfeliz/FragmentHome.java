package com.example.pinheiro.serfeliz;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pinheiro.serfeliz.bancointerno.BD;
import com.example.pinheiro.serfeliz.bancointerno.Usuario;
import com.example.pinheiro.serfeliz.clientetelaconvidados.TelaConvidado;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {

    private static final int PIC_CROP =1 ;
    Button btnAdicionarContato;
TextView textView,txtMinhasAnotacoes;
Cursor c;
ArrayList<String> contacts;
ImageView imgFotoCapaResumo,imgFotoPerfil;

LinearLayout liResumoConvidados,liMinhasAnotacoes,liResumoCalculadora,liMinhasTarefas;

    private String EVENT_DATE_TIME = "19-04-19 10:30:00";
    private String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
    private String DATE_FORMAT_DATA = "dd-MM-yyyy";

    private TextView txtDias, txtHoras, txtMinutos, txtSegundos,
                   txtOrcamento,txtDataFesta,txtMensagemFesta,txtTotalConvidados;

    private Handler handler = new Handler();
    private Runnable runnable;
    BD bd;
    int gramas = 0;
    private static final int PICK_IMAGE = 100;

    private static final int  PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);





        txtDias = (TextView) view.findViewById(R.id.txt_Dias);
        txtHoras = (TextView) view.findViewById(R.id.txt_Horas);
        txtMinutos = (TextView) view.findViewById(R.id.txt_Minutos);
        txtSegundos = (TextView) view.findViewById(R.id.txt_Segundos);
        txtOrcamento= (TextView) view.findViewById(R.id.txt_orcamento);
        txtDataFesta = (TextView)view.findViewById(R.id.txt_Data_Festa);
        txtMensagemFesta = (TextView)view.findViewById(R.id.txt_Mensagem_Festa);
        txtTotalConvidados = (TextView)view.findViewById(R.id.txt_Total_Convidados);
        txtMinhasAnotacoes = (TextView)view.findViewById(R.id.txt_Minhas_Anotacoes);
        imgFotoCapaResumo  =(ImageView) view.findViewById(R.id.foto_Capa_Resumo);
        liMinhasAnotacoes = (LinearLayout)view.findViewById(R.id.li_Minhas_Anotacoes);
        liResumoConvidados  = (LinearLayout)view.findViewById(R.id.li_Resumo_Convidados);
        liResumoCalculadora = (LinearLayout)view.findViewById(R.id.li_Resumo_Calculadora);
        liMinhasTarefas = (LinearLayout)view.findViewById(R.id.li_Minhas_tarefas);
        //    imgFotoPerfil = (ImageView)view.findViewById(R.id.foto_Perfil);

        imgFotoCapaResumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               inserirImagemCapa();
            }
        });

        /*
        imgFotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                AuthUI.getInstance()
                        .signOut(getContext())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {

                                startActivity(new Intent(getContext(),TelaEspera.class));
                                getActivity().finish();

                                // ...
                            }
                        });




                inserirImagemPerfil();

            }
        });
*/
        txtMensagemFesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(),TelaEditarMensagem.class));
            }
        });

        atualizarResumo();
        countDownStart();


        /* copiar o seguinte exemplo
                 * https://www.youtube.com/watch?v=IXs9zo687qA
                 *
                 * */



        liResumoConvidados.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(),TelaConvidado.class));


            }

        });


        liMinhasTarefas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(),TelaMinhasTarefas.class));


            }

        });
        liMinhasAnotacoes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(),TelaMinhasAnotacoes.class));


            }

        });


        liResumoCalculadora.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(),TelaCalculadora.class));


            }

        });


        return view;
    }

    private void inserirImagemPerfil() {


        Intent intent
                = new Intent(Intent.ACTION_PICK, Uri.parse("content://media/internal/images/media"));

        startActivityForResult(intent,PICK_IMAGE);


    }

    // link de referência desse trecho de código
    // https://stackoverflow.com/questions/10032003/how-to-make-a-countdown-timer-in-android

    private void countDownStart() {

        runnable = new Runnable() {
            @Override
            public void run() {

                try {
                    handler.postDelayed(this, 1000);


                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                    Date event_date = dateFormat.parse(EVENT_DATE_TIME);
                    Date current_date;
                    current_date = new Date();



                    if (!current_date.after(event_date)) {

                        long diff = event_date.getTime() - current_date.getTime();
                        long Days = diff / (24 * 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;


                        txtDias.setText(String.format("%02d", Days));
                        txtHoras.setText(String.format("%02d", Hours));
                        txtMinutos.setText(String.format("%02d", Minutes));
                        txtSegundos.setText(String.format("%02d", Seconds));

                    } else {


                        handler.removeCallbacks(runnable);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);

    }


    public void onPause() {
        super.onPause();

        countDownStart();
        atualizarResumo();
    }

    private void atualizarResumo() {


                bd = new BD(getContext());

                List<Usuario> list = bd.buscar();

                Usuario user =  (Usuario) list.get(0);

                txtOrcamento.setText(user.getOrcamento());

                EVENT_DATE_TIME = user.getDataFestaRegressiva();

                txtDataFesta.setText(EVENT_DATE_TIME);

                Toast.makeText(getContext(),EVENT_DATE_TIME , Toast.LENGTH_SHORT).show();

                txtMensagemFesta.setText(user.getMensagem());


                int criancas = Integer.parseInt(user.getConvidadosCriancas());
                int adultos = Integer.parseInt( user.getConvidadosAdultos());


                int totalConvidados = criancas + adultos;
                Toast.makeText(getContext(),"total convidados" + totalConvidados,Toast.LENGTH_SHORT).show();
                txtTotalConvidados.setText(String.valueOf("Total " + totalConvidados));

                txtMinhasAnotacoes.setText(user.getQtdeAnotacoes() + " anotação");
                Glide.with(imgFotoCapaResumo).load(user.getImgCapa()).into(imgFotoCapaResumo);


    }



    public void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }





    public void inserirImagemCapa(){


        Intent intent
                 = new Intent(Intent.ACTION_PICK, Uri.parse("content://media/internal/images/media"));

            startActivityForResult(intent,PICK_IMAGE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK && requestCode == PICK_IMAGE){


                bd = new BD(getContext());

                List<Usuario> list = bd.buscar();

                Usuario user =  (Usuario) list.get(0);

                Uri uri = data.getData();
                String x  = getPatch(uri);

             //   performCrop(uri);

            atualizaCapaResumo(x);

            }


        }

    private void performCrop(Uri picUri) {

        try {

            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            // set crop properties here
            cropIntent.putExtra("crop", true);
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 128);
            cropIntent.putExtra("outputY", 128);
            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);


        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {

            // display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    public String getPatch(Uri uri){

        if (uri == null)return  null;

        String[] projection = {MediaStore.Images.Media.DATA};

        Cursor cursor =
                 getActivity().managedQuery(uri,projection,null,null,null);

        if (cursor != null){

            int column_index = cursor
                               .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }

        return uri.getPath();

    }

    public   void atualizaCapaResumo(String foto){


        try{

            FileInputStream fs = new FileInputStream(foto);

            byte[] imgByte = new byte[fs.available()];

            bd = new BD(getContext());

            List<Usuario> list = bd.buscar();

            Usuario user =  (Usuario) list.get(0);

            fs.read(imgByte);

            //valores.put("imgCapa",imgByte);

            user.setImgCapa(imgByte);

            bd.atualizar(user);
            String imagem = String.valueOf(user.getImgCapa());

           Toast.makeText(getContext(), "imagem atualizada" + imagem,Toast.LENGTH_SHORT).show();


            Glide.with(imgFotoCapaResumo).load(user.getImgCapa()).into(imgFotoCapaResumo);

/*
            Bitmap bt = null;

            byte[] imagemCapa = user.getImgCapa();

            bt = BitmapFactory.decodeByteArray(imagemCapa,0,imagemCapa.length);

            //imgFotoCapaResumo.setImageBitmap(bt);

*/

            Toast.makeText(getContext(),"estou executando",Toast.LENGTH_LONG).show();





            fs.close();


        }catch (IOException e){
            e.printStackTrace();

            Toast.makeText(getContext(), "ocorreu um erro", Toast.LENGTH_SHORT).show();
        }


    }



}
