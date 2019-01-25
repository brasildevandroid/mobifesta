package com.example.pinheiro.serfeliz.clientetelaconvidados;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pinheiro.serfeliz.R;

import java.util.List;


public class AdapterListaConvidados extends RecyclerView.Adapter<AdapterListaConvidados.MeuViewHolder> {


    private List<Convidado>listaConvidados;
    private OnItemClickListener mListener;


    public interface  OnItemClickListener{

        void onMensagemCliente(int position);
        void onLigacaoCliente(int position);
        void onAlterarStatusColeta(int position);
        void onNavegarCliente(int position);

    }


    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;

    }

    public AdapterListaConvidados(List<Convidado> convidado){

        listaConvidados = convidado;
    }


    public static  class MeuViewHolder extends RecyclerView.ViewHolder{


        public TextView nome;
        public TextView celular;
        public TextView statusConvidado;
        public TextView txtGrauParentesco;

        public MeuViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            nome = (TextView) itemView.findViewById(R.id.txt_Convidado_Nome);
            txtGrauParentesco = (TextView)itemView.findViewById(R.id.txt_Convidado_Grau_Parentesco);
            statusConvidado = (TextView)itemView.findViewById(R.id.txt_Status_Convidado);

/*
              mMensagemCliente = (ImageView) itemView.findViewById(R.id.coleta_mensagem_admin);
              mLigarCliente = (ImageView) itemView.findViewById(R.id.coleta_ligar_admin);
              mNavegarCliente = (ImageView)itemView.findViewById(R.id.coleta_endereco_admin);
              */

             // alterarStatusConvidado = (Button) itemView.findViewById(R.id.btn_Alterar_Status_Convidado);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (listener != null){

                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){

                            listener.onMensagemCliente(position);

                        }

                    }
                }
            });

/*

            mLigarCliente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (listener != null){

                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){

                            listener.onLigacaoCliente(position);
                        }

                    }
                }
            });

            */

/*
            alterarStatusConvidado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (listener != null){

                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){

                            listener.onAlterarStatusColeta(position);
                        }

                    }
                }
            });


*/


/*
            mNavegarCliente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (listener != null){

                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){

                            listener.onNavegarCliente(position);
                        }

                    }
                }
            });



*/


        }



    }



    @NonNull
    @Override
    public MeuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.card_convidado,parent,false);

            MeuViewHolder evh = new MeuViewHolder(view,mListener);

        return evh;

    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolder holder, int position) {

        Convidado convidado = listaConvidados.get(position);

        holder.nome.setText(convidado.getNome());
        holder.txtGrauParentesco.setText(convidado.getTipoConvidado());
        holder.statusConvidado.setText(convidado.getStatus());







    }


    @Override
    public int getItemCount() {

        return listaConvidados.size();

      }



}
