package com.example.pinheiro.serfeliz.lojavirtual;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pinheiro.serfeliz.R;

import java.util.ArrayList;
import java.util.List;

import pojos.ItemFornecedor;


public class FragmentDebutante extends Fragment {


    private AdapterItemFornecedor mAdapter;
    private RecyclerView mRecyclerItem;
    private RecyclerView.LayoutManager mLayoutManager;


    public FragmentDebutante() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_debutante,container,false);


        mRecyclerItem = (RecyclerView) view.findViewById(R.id.rec_Debutante);



        buildRecyclerView();


        return view;


    }


    private void buildRecyclerView() {

        mAdapter = new AdapterItemFornecedor(getListaItem());


        String tamanho = String.valueOf(getListaItem());
        Toast.makeText(getContext(),tamanho,Toast.LENGTH_SHORT).show();




                                // Configurando o gerenciador de layout para ser uma lista.
                                mRecyclerItem.setLayoutManager(new GridLayoutManager(getContext(),
                                        2, GridLayoutManager.VERTICAL, false));

                                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                                mRecyclerItem.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerItem.getContext(),
                layoutManager.getOrientation());
        mRecyclerItem.addItemDecoration(dividerItemDecoration);

                                // Adiciona o adapter que irá anexar os objetos à lista.
                                // Está sendo criado com lista vazia, pois será preenchida posteriormente.

                                mLayoutManager = new LinearLayoutManager(getContext());
                                mRecyclerItem.setHasFixedSize(true);

                                mRecyclerItem.setLayoutManager(mLayoutManager);
                                mRecyclerItem.setAdapter(mAdapter);


/*
        mAdapter.setOnItemClickListener(new AdapterItemFornecedor.OnItemClickListener() {

            @Override
            public void onMensagemCliente(int position) {

                mensagemCliente(position);
            }


            @Override
            public void onLigacaoCliente(int position) {
                ligarCliente(position);
            }

            @Override
            public void onAlterarStatusColeta(int position) {

                alertStatusColeta(position);

            }

            @Override
            public void onNavegarCliente(int position) {
                navegarCliente(position);
            }
        });

*/

    }


    public List<ItemFornecedor> getListaItem() {

     List<ItemFornecedor>  item = new ArrayList<>();

        ItemFornecedor  recepcao = new ItemFornecedor("Recepção",R.drawable.ic_recepcao);
        ItemFornecedor  buffet = new ItemFornecedor("Buffet",R.drawable.ic_buffet);
        ItemFornecedor  bolo = new ItemFornecedor("Bolo de Aniversário",R.drawable.ic_bolo);
        ItemFornecedor  tema = new ItemFornecedor("Temas",R.drawable.ic_tema);
        ItemFornecedor  fotografia = new ItemFornecedor("Fotografia & Vídeo",R.drawable.ic_fotografia);
        ItemFornecedor  musica = new ItemFornecedor("Música",R.drawable.ic_musica);
        ItemFornecedor  carro = new ItemFornecedor("Carro do Aniversariante",R.drawable.ic_carro);
        ItemFornecedor  convite = new ItemFornecedor("Convite de Aniversário",R.drawable.ic_convite);
        ItemFornecedor  lembrancas = new ItemFornecedor("Lembrancinha de Aniversário",R.drawable.ic_lembrancinhas);
        ItemFornecedor  decoracao = new ItemFornecedor("Flores & Decoração",R.drawable.ic_flores_decoracao);
        ItemFornecedor  animacao = new ItemFornecedor("Animação & Personagens Vivos",R.drawable.ic_animacao);
        ItemFornecedor  salgados = new ItemFornecedor("Salgados",R.drawable.ic_salgados);
        ItemFornecedor  principe = new ItemFornecedor("Principe",R.drawable.prince);
        ItemFornecedor  tremzinhos = new ItemFornecedor("Estação de Lanches & Outros",R.drawable.ic_trenzinhos);
        ItemFornecedor  barman = new ItemFornecedor("Barman",R.drawable.bartender);
        ItemFornecedor  fantasias = new ItemFornecedor("Fantasia",R.drawable.ic_fantasy);
        ItemFornecedor  toalhasMesas = new ItemFornecedor("Toalhas de Mesas & Mesas com Cadeiras",R.drawable.ic_toalhas_mesas);
        ItemFornecedor  algodao = new ItemFornecedor("Algodão doce & Maçã do Amor",R.drawable.ic_algodao_doce);
        ItemFornecedor  recreacao = new ItemFornecedor("Pula-Pula & outros Brinquedos",R.drawable.ic_recreacao);



        item.add(recepcao);
        item.add(buffet);
        item.add(bolo);
        item.add(tema);
        item.add(principe);
        item.add(carro);
        item.add(salgados);
        item.add(fotografia);
        item.add(musica);
        item.add(convite);
        item.add(lembrancas);
        item.add(decoracao);
        item.add(animacao);
        item.add(tremzinhos);
        item.add(fantasias);
        item.add(toalhasMesas);
        item.add(algodao);
        item.add(recreacao);
        item.add(barman);



        return item;
    }
}
