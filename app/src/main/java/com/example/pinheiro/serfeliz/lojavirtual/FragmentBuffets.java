package com.example.pinheiro.serfeliz.lojavirtual;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pinheiro.serfeliz.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FragmentBuffets extends Fragment {

    AdapterListaBuffets adapterBuffets;
    List<Buffet> listaBuffets;

    TextView txtBuffetCidade;
    Query mQuery;
    RecyclerView recBuffets;
    private FirebaseFirestore mFirestore;
    private static final int LIMIT = 50;
    private RecyclerView.LayoutManager mLayoutManager;


    public FragmentBuffets() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buffet,container,false);
        txtBuffetCidade = (TextView) view.findViewById(R.id.txt_Categoria_Cidade);
        recBuffets = (RecyclerView) view.findViewById(R.id.rec_Buffets);

        buildRecyclerView();

        return view;
    }

    private void buildRecyclerView() {

        listaBuffets  = new ArrayList<Buffet>();
        mFirestore = FirebaseFirestore.getInstance ();
        // TODO(developer): Implement

        adapterBuffets = new AdapterListaBuffets(listaBuffets);


        mFirestore.collection("fornecedor")
                .document("rio de janeiro")
                .collection("buffet")

              //  .whereEqualTo("status", "aberta")

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {


                                Buffet buffet = document.toObject(Buffet.class);

                                listaBuffets.add(buffet);

                               //  String aspaAbre  = "(";
                                // String aspaFecha  = ")";
                                // totalColetas.setText(aspaAbre + exampleList.size()+ aspaFecha + " Coletas em Aberto");

                                // Configurando o gerenciador de layout para ser uma lista.
                                recBuffets.setLayoutManager(new GridLayoutManager(getContext(),
                                        2, GridLayoutManager.VERTICAL, false));

                                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                                recBuffets.setLayoutManager(layoutManager);

                                // Adiciona o adapter que irá anexar os objetos à lista.
                                // Está sendo criado com lista vazia, pois será preenchida posteriormente.

                                mLayoutManager = new LinearLayoutManager(getContext());
                                recBuffets.setHasFixedSize(true);

                                recBuffets.setLayoutManager(mLayoutManager);
                                recBuffets.setAdapter(adapterBuffets);

                            }

                        }
                    }
                });



adapterBuffets.setOnItemClickListener(new AdapterListaBuffets.OnItemClickListener() {
    @Override
    public void onItemClicado(int position) {


        detalheBuffet(position);


    }
});


    }

    public void detalheBuffet(int position){

        Buffet buffet = listaBuffets.get(position);

            String detalhe = "detalheBuffet";
            String uid = buffet.getConvidadosMaximo();

        Intent intent = new Intent(getContext(), DetalheBuffet.class);
        intent.putExtra(detalhe,uid);

        startActivity(intent);



    }

}
