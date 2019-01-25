package com.example.pinheiro.serfeliz;


import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pojos.Contatoes;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListaContatos extends Fragment {

    RecyclerView recListaContatos;
    private RecyclerView.LayoutManager mLayoutManager;

    AdapterListaContatos adapterContatos;
    List<Contatoes> listaContatoes;

    Cursor c;

    private static final int  PERMISSIONS_REQUEST_READ_CONTACTS = 100;


    public FragmentListaContatos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_lista_contatos,container,false);

        recListaContatos = (RecyclerView)view.findViewById(R.id.rec_Lista_Contatos);




        int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED){

            carregaListaContatos();

        }else{

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACTS

            );
        }




        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS){

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

              carregaListaContatos();

            }else {

                Toast.makeText(getActivity(),"por favor garanta á permissão na lista de contatos",Toast.LENGTH_SHORT).show();


            }
        }
    }

    public void carregaListaContatos(){

        c = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC");


        listaContatoes = new ArrayList<Contatoes>();

        adapterContatos = new AdapterListaContatos(listaContatoes);


        while (c.moveToNext()){

            String contacName =    c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            Contatoes contatoes = new Contatoes();
            contatoes.setNome(contacName);
            listaContatoes.add(contatoes);// + "\n" +
            //   "PhoneNo:" + phoneNumber);

            listaContatoes.add(contatoes);

            //  String aspaAbre  = "(";
            // String aspaFecha  = ")";
            // totalColetas.setText(aspaAbre + exampleList.size()+ aspaFecha + " Coletas em Aberto");

            // Configurando o gerenciador de layout para ser uma lista.
            recListaContatos.setLayoutManager(new GridLayoutManager(getContext(),
                    2, GridLayoutManager.VERTICAL, false));

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            recListaContatos.setLayoutManager(layoutManager);

            // Adiciona o adapter que irá anexar os objetos à lista.
            // Está sendo criado com lista vazia, pois será preenchida posteriormente.

            mLayoutManager = new LinearLayoutManager(getContext());
            recListaContatos.setHasFixedSize(true);

            recListaContatos.setLayoutManager(mLayoutManager);
            recListaContatos.setAdapter(adapterContatos);

            Log.d("lista","contatos" + listaContatoes);

        }

        c.close();




                            }

                        }






