package com.example.pinheiro.serfeliz.lojavirtual;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pinheiro.serfeliz.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FragmentFornecedorFotos extends Fragment {

    ViewPager viewPager;
    List<String> listaFotosFornecedor;
    private FirebaseFirestore mFirestore;
    ViewPagerAdapter viewPagerAdapter;
   static String[] foto;

    public FragmentFornecedorFotos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fornecedor_fotos,container,false);

        listaFotosFornecedor = new ArrayList<>();
        mFirestore = FirebaseFirestore.getInstance();
        listaFotosFornecedor = new ArrayList<>();

        /*
        listaFotosFornecedor.add("https://firebasestorage.googleapis.com/v0/b/serfeliz-4d215.appspot.com/o/buffet1.jpg?alt=media&token=630498c8-bcd3-46cd-b532-18912b743661");
        listaFotosFornecedor.add("https://firebasestorage.googleapis.com/v0/b/serfeliz-4d215.appspot.com/o/buffet2.jpg?alt=media&token=b7366559-9bd9-4fed-818c-57728d96ab4d");
        listaFotosFornecedor.add("https://firebasestorage.googleapis.com/v0/b/serfeliz-4d215.appspot.com/o/buffet3.jpg?alt=media&token=41e8ece9-63e1-4320-807f-aebb47f2b27f");
        listaFotosFornecedor.add("https://firebasestorage.googleapis.com/v0/b/serfeliz-4d215.appspot.com/o/buffet4.jpg?alt=media&token=e7b63e50-6bbc-4e1c-a55a-4224b2368ac5");
        listaFotosFornecedor.add("https://firebasestorage.googleapis.com/v0/b/serfeliz-4d215.appspot.com/o/buffet5.jpg?alt=media&token=cba9f7d5-9963-41c3-8aa5-efe591afe917");

*/
     //   gravaFotos();



        viewPager = (ViewPager) view.findViewById(R.id.slide_Foto);

 try{


    mFirestore.collection("fornecedor")
            .document("rio de janeiro")
            .collection("buffet")
            .document()
            .collection("fotos")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                      if (task.isSuccessful()){

                          for (QueryDocumentSnapshot document : task.getResult()) {
                              Log.d("TAG", document.getId());

                              Foto fotos =document.toObject(Foto.class);

                              String fotosa = fotos.getUrl();

                              listaFotosFornecedor.add(fotosa);

                              String tamanho = String.valueOf(listaFotosFornecedor.size());

                              Toast.makeText(getContext(),tamanho,Toast.LENGTH_SHORT).show();
                              viewPagerAdapter = new ViewPagerAdapter(getContext(),listaFotosFornecedor);
                              viewPager.setAdapter(viewPagerAdapter);


                          }
                      }



                }
            });



}catch (Exception e){

    e.printStackTrace();
}

        // Inflate the layout for this fragment
        return view;
    }

    private void gravaFotos() {

        Foto foto = new Foto();
        Foto foto1 = new Foto();
        Foto foto2 = new Foto();


        foto.setUrl("https://firebasestorage.googleapis.com/v0/b/serfeliz-4d215.appspot.com/o/buffet5.jpg?alt=media&token=cba9f7d5-9963-41c3-8aa5-efe591afe917");
        foto1.setUrl("https://firebasestorage.googleapis.com/v0/b/serfeliz-4d215.appspot.com/o/buffet2.jpg?alt=media&token=b7366559-9bd9-4fed-818c-57728d96ab4d");
        foto2.setUrl("https://firebasestorage.googleapis.com/v0/b/serfeliz-4d215.appspot.com/o/buffet3.jpg?alt=media&token=41e8ece9-63e1-4320-807f-aebb47f2b27f");

        mFirestore.collection("fornecedor")
                .document("rio de janeiro")
                .collection("buffet")
                .document("sampaio buffet")
                .collection("fotos")
                .document()
                .set(foto)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                    }
                });

        mFirestore.collection("fornecedor")
                .document("rio de janeiro")
                .collection("buffet")
                .document("sampaio buffet")
                .collection("fotos")
                .document()
                .set(foto1)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                    }
                });


        mFirestore.collection("fornecedor")
                .document("rio de janeiro")
                .collection("buffet")
                .document("sampaio buffet")
                .collection("fotos")
                .document()
                .set(foto2)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                    }
                });



    }


}
