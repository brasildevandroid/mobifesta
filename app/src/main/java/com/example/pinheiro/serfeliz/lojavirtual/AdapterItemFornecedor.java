package com.example.pinheiro.serfeliz.lojavirtual;


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


import pojos.ItemFornecedor;

public class AdapterItemFornecedor extends RecyclerView.Adapter<AdapterItemFornecedor.MeuViewHolder> {


    private List<ItemFornecedor>listaFornecedor;
    private OnItemClickListener mListener;


    public interface  OnItemClickListener{

        void onItemClicado(int position);


    }


    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;

    }

    public AdapterItemFornecedor(List<ItemFornecedor> item){

        listaFornecedor = item;
    }


    public static  class MeuViewHolder extends RecyclerView.ViewHolder{


        public TextView nomeItem;
        public ImageView imgItem;

        public MeuViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);


            nomeItem = (TextView) itemView.findViewById(R.id.txt_Item_Fornecedor);
            imgItem = (ImageView)itemView.findViewById(R.id.img_Item_Fornecedor);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (listener != null){

                      int position = getAdapterPosition();

                      if (position != RecyclerView.NO_POSITION){

                          listener.onItemClicado(position);
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
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fornecedor_loja,parent,false);

            MeuViewHolder evh = new MeuViewHolder(view,mListener);

        return evh;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItemFornecedor.MeuViewHolder holder, int position) {

      ItemFornecedor item = listaFornecedor.get(position);


      holder.nomeItem.setText(item.getNome());
      holder.imgItem.setImageResource(item.getImagem());



    }


    @Override
    public int getItemCount() {

        return listaFornecedor.size();

      }



}
