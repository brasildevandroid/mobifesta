package com.example.pinheiro.serfeliz;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 *
 */
public class FragmentCadastroBloqueado extends android.support.v4.app.Fragment {

    protected AlertDialog alerta;
    Button btnEnviarCadastroPessoaFisica;

    public FragmentCadastroBloqueado() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_cadastro_bloqueado,container,false);

   //
        //     btnEnviarCadastroPessoaFisica = (Button) view.findViewById(R.id.btn_Enviar_Cadastro_Pessoa_Fisica);

/*
        btnEnviarCadastroPessoaFisica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertCadastroPessoaFisica();
            }
        });
*/

        return view;


    }



    public void alertCadastroPessoaFisica(){

        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //define o titulo
        //builder.setTitle("Coleta enviada com sucesso!");

        builder.setMessage("Deseja revisar os dados antes de enviar para ánalise ?");


        //define um botão como positivo
        builder.setNegativeButton("revisar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {



                /*
                EnviaColeta.enviaCadastroColeta();
                alerta.dismiss();


                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container_tela_principal,new FragmentHome());
                ft.commit();

                */

                alerta.dismiss();
            }
        });


        //define um botão como positivo
        builder.setPositiveButton("confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                /*
                EnviaColeta.enviaCadastroColeta();
                alerta.dismiss();


                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container_tela_principal,new FragmentHome());
                ft.commit();

                */

                getActivity().startActivity(new Intent(getActivity(),TelaProfissionalTeste.class));
                alerta.dismiss();
            }
        });





        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }



}
