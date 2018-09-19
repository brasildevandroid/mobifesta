package com.example.pinheiro.serfeliz;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class FragmentCadastrosPendentes extends Fragment {

    private AdapterCadastrosPendentes mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static ArrayList<DadosGeraisProfissionalFisica> listaCadastroPendente;
    private RecyclerView mRecyclerView;
    private TextView totalColetas;
    //static  private LineAdapterLojas mAdapter;
    static private FirebaseFirestore mFirestore;
    static private FirebaseUser mUser;

    protected AlertDialog alerta;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view  = inflater.inflate(R.layout.fragment_cadastros_pendentes,container,false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rec_Cadastros_Pendentes);

        totalColetas = (TextView) view.findViewById(R.id.txt_Total_Cadastros_Pendentes);
        buildRecyclerView();


        return view;

    }



    private void buildRecyclerView() {


        listaCadastroPendente = new ArrayList<>();
        // final List<Coleta> listCadastroColeta = new ArrayList<>();

        mFirestore = FirebaseFirestore.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();



        mAdapter = new AdapterCadastrosPendentes(listaCadastroPendente);


       mFirestore.collection("plataforma")
                .document("profissional")
                .collection("pendentes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {


                                DadosGeraisProfissionalFisica dados = document.toObject(DadosGeraisProfissionalFisica.class);

                                listaCadastroPendente.add(dados);

                                String aspaAbre  = "(";
                                String aspaFecha  = ")";
                                totalColetas.setText(aspaAbre + listaCadastroPendente.size()+ aspaFecha + " Cadastros Pendentes");



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
                                mRecyclerView.setAdapter(mAdapter);

                            }

                        }
                    }
                });


        mAdapter.setOnItemClickListener(new AdapterCadastrosPendentes.OnItemClickListener() {

            @Override
            public void onMensagemCliente(int position) {

                mensagemCliente(position);
            }


            @Override
            public void onLigacaoCliente(int position) {

                ligarCliente(position);
            }

            @Override
            public void onAlterarCadastroPendente(int position) {

                alteraStatusColeta(position);

            }


        });

    }


    public void ligarCliente(int position){

        String numero  = listaCadastroPendente.get(position).getCelular();

        mAdapter.notifyDataSetChanged();


        Uri uri = Uri.parse("tel:"+numero);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);

        startActivity(intent);

    }

    public void alteraStatusColeta(int position){


        mFirestore = FirebaseFirestore.getInstance();

       DadosGeraisProfissionalFisica dadosGeraisProfissionalFisica =  listaCadastroPendente.get(position);

        String status= dadosGeraisProfissionalFisica.getStatus();

        DocumentReference alterarDados =    mFirestore.collection("plataforma")
                .document("profissional")
                .collection("pendentes")
                .document(status);

        alterarDados
                .update("status", "ativo")
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(getActivity(),"status alterado com sucesso",Toast.LENGTH_SHORT).show();                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("", "Error updating document", e);
                    }
                });

        Toast.makeText(getActivity(),status,Toast.LENGTH_LONG).show();
        mAdapter.notifyDataSetChanged();

    }


    public void mensagemCliente(int position){

        String numero  = listaCadastroPendente.get(position).getCelular();


        mAdapter.notifyDataSetChanged();


        Uri uri = Uri.parse("smsto:" + numero);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(i, ""));

    }



    public void alertStatusColeta(int position){

        final DocumentReference alterarCadastroPendente;

        mFirestore = FirebaseFirestore.getInstance();

        DadosGeraisProfissionalFisica dadosGerais =  listaCadastroPendente.get(position);

        final String status = dadosGerais.getStatus();


        alterarCadastroPendente  =    mFirestore.collection("plataforma")
                .document("profissional")
                .collection("pendentes")
                .document(status);


        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //define o titulo
        //builder.setTitle("Coleta enviada com sucesso!");

        builder.setMessage("Deseja alterar o satus da coleta ?");

        //define um botão como positivo
        builder.setPositiveButton("confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                alterarCadastroPendente
                        .update("status", "ativo")
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("", "DocumentSnapshot successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("", "Error updating document", e);
                            }
                        });

                Toast.makeText(getActivity(),status,Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();

                buildRecyclerView();

                alerta.dismiss();



            }
        });

        builder.setNegativeButton("depois", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                alerta.dismiss();


            }
        });




        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }


}
