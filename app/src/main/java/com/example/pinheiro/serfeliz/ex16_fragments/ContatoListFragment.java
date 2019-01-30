package com.example.pinheiro.serfeliz.ex16_fragments;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pinheiro.serfeliz.CustomeArrayAdapter;
import com.example.pinheiro.serfeliz.R;


import java.util.ArrayList;
import java.util.List;



public class ContatoListFragment extends ListFragment
        implements ActionMode.Callback, AdapterView.OnItemLongClickListener {
    ListView mListView;
    List<Contato> mContatos;
    ArrayAdapter<Contato> mAdapter;
    ActionMode mActionMode;

    Cursor c;


    private static final int  PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mContatos = carregarContatos();



        int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED){

            carregarContatos();

        }else{

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACTS

            );
        }




    }




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView = getListView();
        limparBusca();
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS){

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

                carregarContatos();

            }else {

                Toast.makeText(getContext(),"por favor garanta á permissão na lista de contatos",Toast.LENGTH_SHORT).show();


            }
        }
    }

    private List<Contato> carregarContatos() {

        List<Contato> contatoes = new ArrayList<Contato>();



        c = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC");


        while (c.moveToNext()) {

            String contacName = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

           Contato contato = new Contato(contacName,phoneNumber,4);

           contatoes.add(contato);


        }

        c.close();


        return contatoes;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (mActionMode == null) {
            Activity activity = getActivity();
            if (activity instanceof AoClicarNoHotel) {

                Contato contato = (Contato) l.getItemAtPosition(position);

                AoClicarNoHotel listener = (AoClicarNoHotel) activity;
                listener.clicouNoHotel(contato);
            }
        } else {
            int checkedCount = atualizarItensMarcados(mListView, position);
            if (checkedCount == 0) {
                mActionMode.finish();
            }
        }
    }

    public void buscar(String s) {
        if (s == null || s.trim().equals("")) {
            limparBusca();
            return;
        }

        List<Contato> hoteisEncontrados = new ArrayList<Contato>(mContatos);

        for (int i = hoteisEncontrados.size()-1; i >= 0; i--) {
            Contato contato = hoteisEncontrados.get(i);
            if (! contato.nome.toUpperCase().contains(s.toUpperCase())) {
                hoteisEncontrados.remove(contato);
            }
        }

        mListView.setOnItemLongClickListener(null);
        mAdapter = new ArrayAdapter<Contato>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                hoteisEncontrados);
        setListAdapter(mAdapter);
    }

    public void limparBusca() {
        mListView.setOnItemLongClickListener(this);

        mAdapter = new MultiSelectAdapter<Contato>(getActivity(),
                android.R.layout.simple_list_item_1,
                mContatos);

        setListAdapter(mAdapter);
    }

    public void adicionar(Contato contato) {
        mContatos.add(contato);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.menu_delete_list, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.acao_delete) {
            SparseBooleanArray checked = mListView.getCheckedItemPositions();

            for (int i = checked.size()-1; i >= 0; i--) {
                if (checked.valueAt(i)) {
                    mContatos.remove(checked.keyAt(i));
                }
            }
            actionMode.finish();
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        mActionMode = null;
        mListView.clearChoices();
        mAdapter.notifyDataSetChanged();
        mListView.setChoiceMode(ListView.CHOICE_MODE_NONE);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

        boolean consumed = (mActionMode == null);

        if (consumed) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();

            mActionMode = activity.startSupportActionMode(this);
            mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

            mListView.setItemChecked(i, true);
            atualizarItensMarcados(mListView, i);
        }
        return consumed;
    }

    private int atualizarItensMarcados(ListView l, int position) {
        SparseBooleanArray checked = l.getCheckedItemPositions();

        l.setItemChecked(position, l.isItemChecked(position));

        int checkedCount = 0;
        for (int i = 0; i < checked.size(); i++) {
            if (checked.valueAt(i)) {
                checkedCount++;
            }
        }

        String selecionados = getResources().getQuantityString(
                R.plurals.numero_selecionados,
                checkedCount, checkedCount);
        mActionMode.setTitle(selecionados);

        return checkedCount;
    }

    public interface AoClicarNoHotel {
        void clicouNoHotel(Contato contato);
    }

}

