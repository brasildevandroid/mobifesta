/**
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 package com.example.pinheiro.serfeliz.lojavirtual;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.pinheiro.serfeliz.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * RecyclerView adapter for a list of Restaurants.
 */
public class ProdutoAdapter extends FirestoreAdapter<ProdutoAdapter.ViewHolder> {

    public interface OnRestaurantSelectedListener {

        void onRestaurantSelected(DocumentSnapshot restaurant);

    }

    private OnRestaurantSelectedListener mListener;

    public ProdutoAdapter(Query query, OnRestaurantSelectedListener listener) {
        super(query);
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_restaurant_teste, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_Produto_Capa)
        ImageView imgProdutoCapa;

        @BindView(R.id.txt_Produto_Descricao)
        TextView txtProdutoDescricao;


        @BindView(R.id.img_Foto_Profissional)
        ImageView imgFotoProfissional;

        @BindView(R.id.txt_Produto_Detalhes)
        TextView txtProdutoDetalhes;

        @BindView(R.id.txt_Nome_Profissional)
        TextView txtNomeProfissional;

        @BindView(R.id.txt_Produto_Cidade)
        TextView txtProdutoCidade;



        @BindView(R.id.produto_item_rating)
        MaterialRatingBar ratingBar;

        @BindView(R.id.produto_item_num_ratings)
        TextView numRatingsView;


        @BindView(R.id.txt_Produto_Categoria)
        TextView txtProdutoCategoria;

        @BindView(R.id.txt_Quant_Pessoas)
        TextView txtQuantPessoas;


        @BindView(R.id.txt_Produto_Preco)
        TextView txtProdutoPreco;





        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final DocumentSnapshot snapshot,
                         final OnRestaurantSelectedListener listener) {

            Produto produto = snapshot.toObject(Produto.class);
            Resources resources = itemView.getResources();

            // Load image
            Glide.with(imgProdutoCapa.getContext())
                    .load(produto.getFoto())
                    .into(imgProdutoCapa);

            txtNomeProfissional.setText(produto.getNome());
            txtProdutoCategoria.setText(produto.getCategoria());
            txtProdutoCidade.setText(produto.getCidade());
            ratingBar.setRating((float) produto.getAvgRating());
            numRatingsView.setText(resources.getString(R.string.fmt_num_ratings,
                    produto.getNumRatings()));
            txtProdutoPreco.setText(RestaurantUtil.getPriceString(produto));
            txtProdutoDetalhes.setText(produto.getCategoria());

            txtProdutoDescricao.setText("\"Buffets para casamento,aniversários venha conferir e fazer uma degustação conosco nós somos focados na qualidade...\"");

            txtQuantPessoas.setText("60 pessoas");
            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onRestaurantSelected(snapshot);
                    }
                }
            });
        }

    }
}
