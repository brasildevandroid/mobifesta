package com.example.pinheiro.serfeliz.barradepesquisa;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pinheiro.serfeliz.AdapterListaContatos;
import com.example.pinheiro.serfeliz.CustomeArrayAdapter;
import com.example.pinheiro.serfeliz.R;

import java.util.ArrayList;

import pojos.Contatoes;

public class NovaPesquisaContatos extends AppCompatActivity {


    EditText editsearch;
    ListView listView;
    private ArrayList<String> mItems;

    AdapterListaContatos adapterContatos;

    ArrayList<String> contatos;

    Cursor c;


    private static final int  PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_pesquisa_contatos);

        editsearch = (EditText)findViewById(R.id.editText1);
        listView = (ListView)findViewById(R.id.listView1);



        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED){

            carregaListaContatos();

        }else{

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACTS

            );
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS){

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

                carregaListaContatos();

            }else {

                Toast.makeText(this,"por favor garanta á permissão na lista de contatos",Toast.LENGTH_SHORT).show();


            }
        }
    }

    public void carregaListaContatos() {

        c = this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC");


        mItems = new ArrayList<String>();

        while (c.moveToNext()) {

            String contacName = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            Contatoes contatoes = new Contatoes();

            contatoes.setNome(contacName);
            contatoes.setCelular(phoneNumber);
            String contatoAgenda = contacName + phoneNumber;

                mItems.add(contatoes.getNome());

                listView.setAdapter(new CustomeArrayAdapter(NovaPesquisaContatos.this, mItems));




            }

            c.close();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {


                    String contatoAgenda = mItems.get(position);

                  //  String[] arrayValores = contatoAgenda.split(" ");

                   // Toast.makeText(NovaPesquisaContatos.this,contatoAgenda,Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(NovaPesquisaContatos.this, SubActivity.class);

                    intent.putExtra("nome", contatoAgenda);

                    startActivity(intent); //when you click,
                    //open new activity and intent text


                }



            });


        editsearch.addTextChangedListener(new TextWatcher() { //edit search
            //Event when changed word on EditTex
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ArrayList<String> temp = new ArrayList<String>();


                int textlength = editsearch.getText().length();
                temp.clear();
                for (int i = 0; i < mItems.size(); i++) {
                    if (textlength <= mItems.get(i).length())

                    {
                        if (editsearch.getText().toString().equalsIgnoreCase(
                                (String)
                                        mItems.get(i).subSequence(0,
                                                textlength))) {
                            temp.add(mItems.get(i));
                        }
                    }
                }

                listView.setAdapter(new CustomeArrayAdapter(NovaPesquisaContatos.this, temp));
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

    }




    }


