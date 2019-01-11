package com.example.pinheiro.serfeliz.lojavirtual;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pinheiro.serfeliz.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import pojos.Avaliacao;
import pojos.Fornecedor;

public class DetalheBuffet extends AppCompatActivity {

    private FirebaseFirestore mFirestore;
    private DocumentReference mRestaurantRef;
    TextView txtNomeBuffet;
    MaterialRatingBar avaliacaoFornecedor;
    TextView txtNumeroAvaliacoes;
    RecyclerView recAvalicoesFornecedores;
    List<Avaliacao> listaAvaliacao;
    private RecyclerView.LayoutManager mLayoutManager;
    Button btnSolicitarOrçamento;

    AdapterListaAvaliacao adapterAvaliacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_buffet);


        mFirestore = FirebaseFirestore.getInstance();


        String buffet = getIntent().getExtras().getString("detalheBuffet");
        txtNomeBuffet = (TextView) findViewById(R.id.txt_Nome_Buffet);
        avaliacaoFornecedor = (MaterialRatingBar) findViewById(R.id.avaliacao_Fornecedor);
        txtNumeroAvaliacoes = (TextView) findViewById(R.id.txtNumero_Avaliacoes);
        recAvalicoesFornecedores = (RecyclerView)findViewById(R.id.rec_Avaliacoes_Fornecedor);
        btnSolicitarOrçamento = (Button) findViewById(R.id.btn_Solicitar_Orcamento);


        btnSolicitarOrçamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(DetalheBuffet.this,"fui clicado",Toast.LENGTH_SHORT).show();


                startActivity(new Intent(DetalheBuffet.this,SolicitarOrcamento.class));

            }
        });


       // recAvalicoesFornecedores.setNestedScrollingEnabled(false);

            if (buffet != null){

                Toast.makeText(this,buffet,Toast.LENGTH_SHORT).show();
                setDetalhesBuffet(buffet);

            }


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_Fotos_Profissional,new FragmentFornecedorFotos())
                .commit();

        buildRecyclerView();


    }

    private void setDetalhesBuffet(String uid) {

        DocumentReference docRef = mFirestore.collection("fornecedor")
                .document("rio de janeiro")
                .collection("buffet")
                .document("sampaio buffet");


        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Fornecedor fornecedor = documentSnapshot.toObject(Fornecedor.class);

                String nome = fornecedor.getNome();
                int numAvaliacoes = fornecedor.getNumeroAvaliacoes();

                String aspaEsquerda = "(";
                String aspaDireita = ")";

                String num = String.valueOf(numAvaliacoes);
                txtNomeBuffet.setText(nome);
                avaliacaoFornecedor.setRating((float) fornecedor.getAvaliacao());
                txtNumeroAvaliacoes.setText(aspaEsquerda + num + aspaDireita);

            }
        });

    }


    private void buildRecyclerView() {

        listaAvaliacao = new ArrayList<>();

        mFirestore = FirebaseFirestore.getInstance();
        // TODO(developer): Implement

        adapterAvaliacao = new AdapterListaAvaliacao(listaAvaliacao);

        mFirestore.collection("fornecedor")
                .document("rio de janeiro")
                .collection("buffet")
                .document("sampaio buffet")
                .collection("avaliacao")

                //  .whereEqualTo("status", "aberta")

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {


                                Avaliacao avaliacao = document.toObject(Avaliacao.class);
                                listaAvaliacao.add(avaliacao);


                                LinearLayoutManager layoutManager
                                        = new LinearLayoutManager(DetalheBuffet.this, LinearLayoutManager.HORIZONTAL, false);

                                recAvalicoesFornecedores.setLayoutManager(layoutManager);

                                // Está sendo criado com lista vazia, pois será preenchida posteriormente.
                                recAvalicoesFornecedores.setHasFixedSize(true);
                                recAvalicoesFornecedores.setAdapter(adapterAvaliacao);

                            }

                        }
                    }
                });


         }

    }
