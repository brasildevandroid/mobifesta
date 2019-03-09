package com.example.pinheiro.serfeliz;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class AdapterListaCalculadora extends RecyclerView.Adapter<AdapterListaCalculadora.MeuViewHolder> {


    private List<String>listaCalculadora;
    private OnItemClickListener mListener;


    public interface  OnItemClickListener{

        void onMensagemCliente(int position);


    }


    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;

    }

    public AdapterListaCalculadora(List<String> calculadora){

        listaCalculadora = calculadora;
    }


    public static  class MeuViewHolder extends RecyclerView.ViewHolder{


        public TextView itemCalculadora;

        public MeuViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            itemCalculadora = (TextView) itemView.findViewById(R.id.txt_Item_Calculadora);

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
                LayoutInflater.from(parent.getContext()).inflate(R.layout.card_calculadora,parent,false);

            MeuViewHolder evh = new MeuViewHolder(view,mListener);

        return evh;

    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolder holder, int position) {

        String item = listaCalculadora.get(position);


        holder.itemCalculadora.setText(item);

    }

    @Override
    public int getItemCount() {

        return listaCalculadora.size();

      }



}
