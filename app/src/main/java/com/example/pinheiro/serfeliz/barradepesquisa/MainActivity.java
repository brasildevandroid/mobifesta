package com.example.pinheiro.serfeliz.barradepesquisa;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.pinheiro.serfeliz.R;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {


    Cursor c;
    private static final int  PERMISSIONS_REQUEST_READ_CONTACTS = 100;


    private SearchManager searchManager;
    private SearchView searchView;
    private MyExpandableListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<ParentRow> parentList = new ArrayList<ParentRow>();
    private ArrayList<ParentRow> showTheseParentList = new ArrayList<ParentRow>();
    private MenuItem searchItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mostraResultado();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        parentList = new ArrayList<ParentRow>();
        showTheseParentList = new ArrayList<ParentRow>();

         loadData();

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED){

            carregaListaContatos();

        }else{

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACTS

            );
        }




        // The app will crash if display list is not called here.
        displayList();

        // This expands the list.
        expandAll();


    }

    private void mostraResultado() {


        int tamanho = 100;
        int count = 0;
        Random rand = new Random();
        int aleatorio = 100;

        while (count < aleatorio){

            int  n = rand.nextInt((aleatorio) ); //+ 1);

            String  resultado = String.valueOf(n);

            count++;

            Log.d("resultado","(" + count+ ")" + resultado);



        }



    }


    private void loadData() {

        ArrayList<ChildRow> childRows = new ArrayList<ChildRow>();
        childRows = carregaListaContatos();


        ParentRow parentRow = null;

        parentRow = new ParentRow("lista de contatos",childRows);

        parentList.add(parentRow);


        childRows.add(new ChildRow(R.drawable.bartender
                ,"   Lorem ipsum dolor sit amet, consectetur adipiscing elit."));

        parentRow = new ParentRow("First Group", childRows);


        /*


        childRows = new ArrayList<ChildRow>();
        childRows.add(new ChildRow(R.drawable.bartender
                , "Fido is the name of my dog."));
        childRows.add(new ChildRow(R.drawable.bartender
                , "Add two plus two."));
        parentRow = new ParentRow("Second Group", childRows);
        parentList.add(parentRow);
*/
    }


    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            myList.expandGroup(i);
        } //end for (int i = 0; i < count; i++)
    }

    private void displayList() {

     //   loadData();

        myList = (ExpandableListView) findViewById(R.id.expandableListView_search);
        listAdapter = new MyExpandableListAdapter(MainActivity.this,parentList);

        myList.setAdapter(listAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo
                (searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.requestFocus();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listAdapter.filterData(newText);
        expandAll();
        return false;
    }


    public ArrayList<ChildRow>carregaListaContatos(){

        ArrayList<ChildRow> child = new ArrayList<>();

        c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC");



        while (c.moveToNext()) {

            String contacName = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            String phoneNumber = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            ChildRow childRow = new ChildRow(R.drawable.bartender,contacName);

            child.add(childRow);

            //  String aspaAbre  = "(";
            // String aspaFecha  = ")";
            // totalColetas.setText(aspaAbre + exampleList.size()+ aspaFecha + " Coletas em Aberto");


        }

        c.close();

        String tamanho  =  String.valueOf(child.size());
        Toast.makeText(this, "lista tamanho:" + tamanho , Toast.LENGTH_SHORT).show();


        return child;





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



}
