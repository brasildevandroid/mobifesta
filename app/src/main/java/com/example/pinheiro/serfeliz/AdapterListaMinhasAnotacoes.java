package com.example.pinheiro.serfeliz;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;

import pojos.Anotacoes;


public class AdapterListaMinhasAnotacoes extends RecyclerView.Adapter<AdapterListaMinhasAnotacoes.MeuViewHolder> {


    private List<Anotacoes>listaAnotacoes;
    private OnItemClickListener mListener;


    public interface  OnItemClickListener{

        void onMensagemCliente(int position);


    }


    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;

    }

    public AdapterListaMinhasAnotacoes(List<Anotacoes> anotacoes){

        listaAnotacoes = anotacoes;
    }


    public static  class MeuViewHolder extends RecyclerView.ViewHolder{


        public TextView titulo;
        public TextView descricao;

        public MeuViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            titulo = (TextView) itemView.findViewById(R.id.txt_Titulo_Anotacoes);
            descricao = (TextView)itemView.findViewById(R.id.txt_Descricao_Anotacoes);


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



        }



    }



    @NonNull
    @Override
    public MeuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.card_minhas_anotacoes,parent,false);

            MeuViewHolder evh = new MeuViewHolder(view,mListener);

        return evh;

    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolder holder, int position) {

        Anotacoes anotacao = listaAnotacoes.get(position);

        holder.titulo.setText(anotacao.getTitulo());
        holder.descricao.setText(anotacao.getDescricao());








    }


    @Override
    public int getItemCount() {

        return listaAnotacoes.size();

      }



}
