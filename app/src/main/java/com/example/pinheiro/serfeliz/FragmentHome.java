package com.example.pinheiro.serfeliz;


import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pinheiro.serfeliz.bancointerno.BancoInternoResumo;
import com.example.pinheiro.serfeliz.bancointerno.PostContract;
import com.example.pinheiro.serfeliz.clientetelaconvidados.TelaConvidado;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {

Button btnAdicionarContato;
TextView textView;
Cursor c;
ArrayList<String> contacts;

LinearLayout liResumoConvidados;

    private String EVENT_DATE_TIME = "2019-04-19 10:30:00";
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private TextView txtDias, txtHoras, txtMinutos, txtSegundos;
    private Handler handler = new Handler();
    private Runnable runnable;


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




        countDownStart();



        


        /* copiar o seguinte exemplo
                 * https://www.youtube.com/watch?v=IXs9zo687qA
                 *
                 * */


        liResumoConvidados  = (LinearLayout)view.findViewById(R.id.li_Resumo_Convidados);


        liResumoConvidados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), TelaConvidado.class));


            }

        });


        return view;
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
                        //

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


    public void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }

}
