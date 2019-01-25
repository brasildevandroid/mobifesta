package com.example.pinheiro.serfeliz;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import pojos.Contatoes;

public class AdapterListaContatos extends RecyclerView.Adapter<AdapterListaContatos.MeuViewHolder> {


    private List<Contatoes> listaContatoes;
    private OnItemClickListener mListener;


    public interface  OnItemClickListener{

        void onItemClicado(int position);


    }


    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;

    }

    public AdapterListaContatos(List<Contatoes> contatoes){

        listaContatoes = contatoes;
    }


    public static  class MeuViewHolder extends RecyclerView.ViewHolder{


        public TextView txtNomeContato;




        public ImageView imgContato;



        public MeuViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);



            imgContato = (ImageView)itemView.findViewById(R.id.img_Contato);
            txtNomeContato = (TextView)itemView.findViewById(R.id.txt_Nome_Contato);




/*
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

*/

                    }





    }



    @NonNull
    @Override
    public MeuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contato,parent,false);

            MeuViewHolder evh = new MeuViewHolder(view,mListener);

        return evh;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListaContatos.MeuViewHolder holder, int position) {

      Contatoes contatoes = listaContatoes.get(position);



/*
        // Load image
        Glide.with(holder.imgContato)
                .load(contatoes.getFoto())
                .into(holder.imgContato);
*/


       holder.txtNomeContato.setText(contatoes.getNome());





    }


    @Override
    public int getItemCount() {

        return listaContatoes.size();

      }



}
