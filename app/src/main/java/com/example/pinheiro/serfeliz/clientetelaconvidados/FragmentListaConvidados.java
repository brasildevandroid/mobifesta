package com.example.pinheiro.serfeliz.clientetelaconvidados;


import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.pinheiro.serfeliz.R;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListaConvidados extends Fragment {

    AdapterListaConvidados adapterListaContatos;
    private static ArrayList<Convidado> listaConvidados;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    //static  private LineAdapterLojas mAdapter;
    String adulto,crianca;
    static private FirebaseFirestore mFirestore;
    static private FirebaseUser mUser;
    TextView txtTotalAdultos,txtTotalCriancas;
    BD bd;
    String total;

    public FragmentListaConvidados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_lista_convidados,container,false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerListaConvidados);
        txtTotalAdultos = (TextView)view.findViewById(R.id.txt_Total_Adultos);
        txtTotalCriancas = (TextView) view.findViewById(R.id.txt_Total_Criancas);


        bd = new BD(getContext());



        buildRecyclerView();



        return view;
    }

    @Override
    public void onPause() {
        super.onPause();


      //  buildRecyclerView();


    }

    private void buildRecyclerView() {


        listaConvidados = new ArrayList<>();

        mFirestore = FirebaseFirestore.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        String userUid = mUser.getUid();


           adapterListaContatos = new AdapterListaConvidados(listaConvidados);


        mFirestore.collection("cliente")
                .document(userUid)
                .collection("convidados")

                //.whereEqualTo("status", "aberta")


                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d("documentos", document.getId() + " => " + document.getData());

                                Convidado convidado = document.toObject(Convidado.class);
                                listaConvidados.add(convidado);

                                // Configurando o gerenciador de layout para ser uma lista.
                                mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),
                                        2, GridLayoutManager.VERTICAL, false));


                                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                                mRecyclerView.setLayoutManager(layoutManager);

                                // Adiciona o adapter que irá anexar os objetos à lista.
                                // Está sendo criado com lista vazia, pois será preenchida posteriormente.

                                mLayoutManager = new LinearLayoutManager(getContext());

                                mRecyclerView.setHasFixedSize(true);
                                mRecyclerView.setLayoutManager(mLayoutManager);
                                mRecyclerView.setAdapter(adapterListaContatos);


                            }

                            MyTelaAtualizaConfirmados atualizaConfirmados = new MyTelaAtualizaConfirmados();
                            atualizaConfirmados.execute();
                        } else {
                            Log.d("", "Error getting documents: ", task.getException());
                        }
                    }
                });


        adapterListaContatos.setOnItemClickListener(new AdapterListaConvidados.OnItemClickListener() {
            @Override
            public void onMensagemCliente(int position) {

                mostraDetalhesConvidado(position);
            }

            @Override
            public void onLigacaoCliente(int position) {

            }

            @Override
            public void onAlterarStatusColeta(int position) {

            }

            @Override
            public void onNavegarCliente(int position) {

            }
        });

    }

    private void mostraDetalhesConvidado(int position) {


        Convidado convidado = (Convidado) listaConvidados.get(position);

        Toast.makeText(getContext(), "detalhes " + convidado.getCelularConvidado(), Toast.LENGTH_SHORT).show();

        Intent it = new Intent(getContext(), ConvidadoDetalheActivity.class);
        it.putExtra(ConvidadoDetalheActivity.EXTRA_CONTATO,convidado);
        startActivity(it);


        adapterListaContatos.notifyDataSetChanged();

    }

    class MyTelaAtualizaConfirmados extends AsyncTask<String, String, String> {




        public MyTelaAtualizaConfirmados(){


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //    alerta_dialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {


           final List<Convidado> listaConfirmados = new ArrayList<>();
            List<Usuario> list = bd.buscar();
            // isso aqui pode fazer ele quebrar
            final   Usuario user =  (Usuario) list.get(0);

            mFirestore = FirebaseFirestore.getInstance();
            mUser = FirebaseAuth.getInstance().getCurrentUser();

            String userUid = mUser.getUid();

            mFirestore = FirebaseFirestore.getInstance();

            mFirestore.collection("cliente")
                    .document(userUid)
                    .collection("convidados")
                    .whereEqualTo("status", "confirmado")


                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    Log.d("documentos", document.getId() + " => " + document.getData());

                                    Convidado convidado = document.toObject(Convidado.class);
                                    listaConfirmados.add(convidado);

                                    total = String.valueOf(listaConfirmados.size());



                                    user.setConfirmados(total);


                                    bd.atualizar(user);
/*

                                    // Configurando o gerenciador de layout para ser uma lista.
                                    mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),
                                            2, GridLayoutManager.VERTICAL, false));


                                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                                    mRecyclerView.setLayoutManager(layoutManager);

                                    // Adiciona o adapter que irá anexar os objetos à lista.
                                    // Está sendo criado com lista vazia, pois será preenchida posteriormente.

                                    mLayoutManager = new LinearLayoutManager(getContext());

                                    mRecyclerView.setHasFixedSize(true);
                                    mRecyclerView.setLayoutManager(mLayoutManager);
                                    mRecyclerView.setAdapter(adapterListaContatos);

*/
                                }
                            } else {
                                Log.d("", "Error getting documents: ", task.getException());
                            }
                        }
                    });



            final List<Convidado> listaConvidados = new ArrayList<Convidado>();

            final int[] totalCrian = {0};
            final int[] totalAdultos = {0};
            final int[] totalPrincipal = {0};


            mFirestore.collection("cliente")
                    .document(userUid)
                    .collection("convidados")

                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    Log.d("documentos", document.getId() + " => " + document.getData());

                                    Convidado convidados = document.toObject(Convidado.class);

                                    listaConvidados.add(convidados);
                                    totalPrincipal[0] =listaConvidados.size();


                                    // se o getAdultos ou criancas for zero ele quebra

                                    if (convidados.getQtdeAcompanhantesCriancas() != "0" && convidados.getQtdeAcompanhantesCriancas() != ""){


                                        int criancas = Integer.parseInt(convidados.getQtdeAcompanhantesCriancas());
                                        totalCrian[0] += criancas;


                                    }

                                    if (convidados.getQtdeAcompanhantesAdultos() != "0"
                                            && convidados.getQtdeAcompanhantesAdultos() != ""){


                                        int adultos = Integer.parseInt(convidados.getQtdeAcompanhantesAdultos());
                                        totalAdultos[0] += adultos;


                                    }

                                }
                            } else {
                                Log.d("", "Error getting documents: ", task.getException());
                            }

                            int total = totalAdultos[0] + totalPrincipal[0];

                            user.setConvidadosCriancas(String.valueOf(totalCrian[0]));
                            user.setConvidadosAdultos(String.valueOf(total));
                            //user.setConfirmados(total);


                            bd.atualizar(user);

                            String totalAdults = user.getConvidadosAdultos();
                            String totalCriancas = user.getConvidadosCriancas();

                            txtTotalCriancas.setText(totalCriancas);
                            txtTotalAdultos.setText(totalAdults);





                        }
                    });

            return null;
        }


        @Override
        protected void onPostExecute(String result) {

        }
    }




}
