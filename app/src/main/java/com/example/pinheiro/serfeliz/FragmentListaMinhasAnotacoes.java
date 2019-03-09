package com.example.pinheiro.serfeliz;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pinheiro.serfeliz.bancointerno.BD;
import com.example.pinheiro.serfeliz.bancointerno.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import pojos.Anotacoes;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListaMinhasAnotacoes extends Fragment {

    AdapterListaMinhasAnotacoes adapterListaAnotacoes;
    private static ArrayList<Anotacoes> listaAnotacoes;
    private RecyclerView recyclerListaMinhasAnotacoes;
    private RecyclerView.LayoutManager mLayoutManager;
    static private FirebaseFirestore mFirestore;
    static private FirebaseUser mUser;

    BD bd;

    public FragmentListaMinhasAnotacoes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_lista_minhas_anotacoes,container,false);

        recyclerListaMinhasAnotacoes = (RecyclerView) view.findViewById(R.id.rec_Lista_Minhas_Anotacoes);


        bd = new BD(getContext());
        List<Usuario> list = bd.buscar();
        Usuario user =  (Usuario) list.get(0);
        String confirmado = user.getConfirmados();
      //  txtTotalConfirmados.setText(confirmado);
        buildRecyclerView();


        return view;
    }

    @Override
    public void onPause() {
        super.onPause();


        buildRecyclerView();


    }

    private void buildRecyclerView() {


        listaAnotacoes = new ArrayList<>();

        mFirestore = FirebaseFirestore.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        String userUid = mUser.getUid();
        String uid = mUser.getUid();
        List<Usuario> list = bd.buscar();
        final   Usuario user =  (Usuario) list.get(0);


         adapterListaAnotacoes = new AdapterListaMinhasAnotacoes(listaAnotacoes);


        mFirestore.collection("cliente")
                .document(userUid)
                .collection("anotacao")




                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d("documentos", document.getId() + " => " + document.getData());

                                Anotacoes anotacao = document.toObject(Anotacoes.class);
                                listaAnotacoes.add(anotacao);

                               String total = String.valueOf(listaAnotacoes.size());


                                user.setQtdeAnotacoes(total);


                                bd.atualizar(user);

                                // Configurando o gerenciador de layout para ser uma lista.
                                recyclerListaMinhasAnotacoes.setLayoutManager(new GridLayoutManager(getContext(),
                                        2, GridLayoutManager.VERTICAL, false));


                                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                                recyclerListaMinhasAnotacoes.setLayoutManager(layoutManager);

                                // Adiciona o adapter que irá anexar os objetos à lista.
                                // Está sendo criado com lista vazia, pois será preenchida posteriormente.

                                mLayoutManager = new LinearLayoutManager(getContext());

                                recyclerListaMinhasAnotacoes.setHasFixedSize(true);
                                recyclerListaMinhasAnotacoes.setLayoutManager(mLayoutManager);
                                recyclerListaMinhasAnotacoes.setAdapter(adapterListaAnotacoes);


                            }
                        } else {
                            Log.d("", "Error getting documents: ", task.getException());
                        }
                    }
                });


        adapterListaAnotacoes.setOnItemClickListener(new AdapterListaMinhasAnotacoes.OnItemClickListener() {
            @Override
            public void onMensagemCliente(int position) {

                mostraDetalhesAnotacao(position);
            }


        });

    }

    private void mostraDetalhesAnotacao(int position) {


        Anotacoes anotacoes = (Anotacoes) listaAnotacoes.get(position);


        Intent it = new Intent(getContext(), AnotacaoDetalheActivity.class);
        it.putExtra(AnotacaoDetalheActivity.EXTRA_CONTATO,anotacoes);
        startActivity(it);


        adapterListaAnotacoes.notifyDataSetChanged();

    }

}
