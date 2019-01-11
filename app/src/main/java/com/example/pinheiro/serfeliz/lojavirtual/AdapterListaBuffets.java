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

import org.w3c.dom.Text;

import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import pojos.ItemFornecedor;

public class AdapterListaBuffets extends RecyclerView.Adapter<AdapterListaBuffets.MeuViewHolder> {


    private List<Buffet>listaBuffets;
    private OnItemClickListener mListener;


    public interface  OnItemClickListener{

        void onItemClicado(int position);


    }


    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;

    }

    public AdapterListaBuffets(List<Buffet> buffet){

        listaBuffets = buffet;
    }


    public static  class MeuViewHolder extends RecyclerView.ViewHolder{


        public TextView txtDescricaoFornecedor;
        public TextView txtFornecedorPreco;
        public  TextView txtFornecedorConvidados;
        public ImageView imgCapaFornecedor;
        public ImageView imgFornecedor;
        public TextView txtFornecedorNome;
        public TextView txtFornecedorBairro;
        public Button btnFornecedorDetalhes;

        MaterialRatingBar ratingBar;


        public MeuViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);


            imgCapaFornecedor = (ImageView)itemView.findViewById(R.id.img_fornecedor_Capa);
            imgFornecedor = (ImageView)itemView.findViewById(R.id.img_Fornecedor);
            txtFornecedorConvidados = (TextView)itemView.findViewById(R.id.txt_Fornecedor_Convidados);
            txtDescricaoFornecedor = (TextView) itemView.findViewById(R.id.txt_Fornecedor_Descricao);
            txtFornecedorPreco = (TextView) itemView.findViewById(R.id.txt_Fornecedor_Preco);
            txtFornecedorNome = (TextView)itemView.findViewById(R.id.txt_Fornecedor_Nome);
            txtFornecedorBairro = (TextView)itemView.findViewById(R.id.txt_Fornecedor_Bairro);
            btnFornecedorDetalhes = (Button) itemView.findViewById(R.id.btn_Fornecedor_Detalhes);
            ratingBar = (MaterialRatingBar)itemView.findViewById(R.id.fornecedor_item_rating);

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
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fornecedor,parent,false);

            MeuViewHolder evh = new MeuViewHolder(view,mListener);

        return evh;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListaBuffets.MeuViewHolder holder, int position) {

      Buffet buffet = listaBuffets.get(position);




        // Load image
        Glide.with(holder.imgCapaFornecedor)
                .load(buffet.getCapa())
                .into(holder.imgCapaFornecedor);



        // Load image
        Glide.with(holder.imgFornecedor)
                .load(buffet.getFoto())
               .into(holder.imgFornecedor);

        String convidados = " a ";
        String preco  = "A partir de R$ ";

       holder.txtDescricaoFornecedor.setText(buffet.getDescricao());
        //holder.imgCapaFornecedor.set
        //holder.imgFornecedor
        holder.txtFornecedorConvidados.setText(buffet.getConvidadosMinimo() +convidados+ buffet.getConvidadosMaximo());
        holder.txtFornecedorPreco.setText(preco + buffet.getPreco());
        holder.txtFornecedorNome.setText(buffet.getNomeFornecedor());
        holder.txtFornecedorBairro.setText(buffet.getBairroFornecedor());
        holder.ratingBar.setRating((float) buffet.getNumRatings());




    }


    @Override
    public int getItemCount() {

        return listaBuffets.size();

      }



}
