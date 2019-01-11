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
import com.google.protobuf.StringValue;

import java.util.ArrayList;
import java.util.List;

import pojos.ItemFornecedor;


public class FragmentCasamento extends Fragment {


    private AdapterItemFornecedor mAdapter;
    private RecyclerView mRecyclerItem;
    private RecyclerView.LayoutManager mLayoutManager;


    public FragmentCasamento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_casamento,container,false);


        mRecyclerItem = (RecyclerView) view.findViewById(R.id.rec_Casamento);




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
        ItemFornecedor  vestido = new ItemFornecedor("Vestido da Noiva",R.drawable.ic_noiva);
        ItemFornecedor  fotografia = new ItemFornecedor("Fotografia & Vídeo",R.drawable.ic_fotografia);


        ItemFornecedor  musica = new ItemFornecedor("Música",R.drawable.ic_musica);
        ItemFornecedor  carro = new ItemFornecedor("Carro de Casamento",R.drawable.ic_carro);
        ItemFornecedor  convite = new ItemFornecedor("Convite de Casamento",R.drawable.ic_convite);
        ItemFornecedor  lembrancas = new ItemFornecedor("Lembranças de Casamento",R.drawable.ic_lembrancinhas);
        ItemFornecedor  decoracao = new ItemFornecedor("Flores & Decoração",R.drawable.ic_flores_decoracao);
        ItemFornecedor  animacao = new ItemFornecedor("Animação",R.drawable.ic_animacao);
        ItemFornecedor  cerimonialista = new ItemFornecedor("Cerimonialista",R.drawable.ic_cerimonialista);
        ItemFornecedor  bolo = new ItemFornecedor("Bolo de Casamento",R.drawable.ic_bolo);
        ItemFornecedor  acessoriosNoiva = new ItemFornecedor("Acessórios da Noiva",R.drawable.ic_noiva);
        ItemFornecedor  noivo = new ItemFornecedor("Terno do Noivo",R.drawable.ic_noiva);
        ItemFornecedor  beleza = new ItemFornecedor("Beleza & Saúde",R.drawable.ic_beleza);
        ItemFornecedor  joalheria = new ItemFornecedor("Joalheria",R.drawable.ic_joias);
        ItemFornecedor  madrinha = new ItemFornecedor("Vestido de Madrinha",R.drawable.ic_noiva);
        ItemFornecedor  padrinho = new ItemFornecedor("Terno do Padrinho",R.drawable.ic_noiva);
        ItemFornecedor  luaDeMel = new ItemFornecedor("Lua de Mel",R.drawable.ic_lua_de_mel);


        item.add(recepcao);
        item.add(buffet);
        item.add(bolo);
        item.add(vestido);
        item.add(acessoriosNoiva);
        item.add(beleza);
        item.add(joalheria);
        item.add(noivo);
        item.add(luaDeMel);
        item.add(carro);
        item.add(cerimonialista);
        item.add(fotografia);
        item.add(musica);
        item.add(convite);
        item.add(lembrancas);
        item.add(decoracao);
        item.add(animacao);
        item.add(padrinho);
        item.add(madrinha);


        return item;
    }
}
