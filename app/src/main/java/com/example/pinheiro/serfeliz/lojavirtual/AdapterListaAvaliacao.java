package com.example.pinheiro.serfeliz.lojavirtual;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pinheiro.serfeliz.R;

import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import pojos.Avaliacao;

public class AdapterListaAvaliacao extends RecyclerView.Adapter<AdapterListaAvaliacao.MeuViewHolder> {


    private List<Avaliacao>listaAvaliacao;
    private OnItemClickListener mListener;


    public interface  OnItemClickListener{

        void onItemClicado(int position);


    }


    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;

    }

    public AdapterListaAvaliacao(List<Avaliacao> avaliacao){

        listaAvaliacao= avaliacao;
    }


    public static  class MeuViewHolder extends RecyclerView.ViewHolder{


        public ImageView imgFotoAvaliacao;
        public TextView txtAvaliacaoNome;
        public TextView txtAvaliacaoTipoFesta;
        public  TextView txtAvaliacaoComentario;

        MaterialRatingBar ratingBar;


        public MeuViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);


            imgFotoAvaliacao = (ImageView)itemView.findViewById(R.id.img_Foto_Avaliacao);
            txtAvaliacaoNome = (TextView) itemView.findViewById(R.id.txt_Avaliacao_Nome);
            txtAvaliacaoTipoFesta = (TextView)itemView.findViewById(R.id.txt_Avaliacao_Tipo_Festa);
            txtAvaliacaoComentario = (TextView) itemView.findViewById(R.id.txt_Avaliacao_Comentario);
            ratingBar = (MaterialRatingBar)itemView.findViewById(R.id.avaliacao_item_rating);



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
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_avaliacao,parent,false);

            MeuViewHolder evh = new MeuViewHolder(view,mListener);

        return evh;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListaAvaliacao.MeuViewHolder holder, int position) {

     Avaliacao avaliacao = listaAvaliacao.get(position);


        // Load image
        Glide.with(holder.imgFotoAvaliacao)
                .load(avaliacao.getUrlFoto())
                .into(holder.imgFotoAvaliacao);


        holder.txtAvaliacaoNome.setText(avaliacao.getNome());
        holder.txtAvaliacaoTipoFesta.setText(avaliacao.getTipoFesta());
        holder.ratingBar.setRating((float) avaliacao.getNumeroAvaliacao());
        holder.txtAvaliacaoComentario.setText(avaliacao.getCometario());





    }


    @Override
    public int getItemCount() {

        return listaAvaliacao.size();

      }



}
