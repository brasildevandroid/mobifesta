package com.example.pinheiro.serfeliz.ex16_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pinheiro.serfeliz.R;

public class FragmentCadastroConvidado extends Fragment {

    public static final String TAG_DETALHE = "tagDetalhe";
    private static final String EXTRA_CONTATO = "contato";

    EditText mTextNome;
    EditText mTextCelular;
    RatingBar mRatingEstrelas;
    ShareActionProvider mShareActionProvider;

    Contato mContato;
    private Spinner sp;


    public static FragmentCadastroConvidado novaInstancia(Contato contato) {
        Bundle parametros = new Bundle();
        parametros.putSerializable(EXTRA_CONTATO, contato);

        FragmentCadastroConvidado fragment = new FragmentCadastroConvidado();
        fragment.setArguments(parametros);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   mContato = (Contato)

           //     getArguments().getSerializable(EXTRA_CONTATO);
    //    setHasOptionsMenu(true);



    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(
                R.layout.fragment_detalhe_hotel, container, false);


        mTextNome     = (EditText) layout.findViewById(R.id.txtNome);
        mTextCelular = (EditText) layout.findViewById(R.id.txtCelular);
        sp = (Spinner)layout.findViewById(R.id.spinner_Grupo);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.grupo, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(adapter);


        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                String posicao  = String.valueOf(position);
                Toast.makeText(getContext(),posicao,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });




           mRatingEstrelas = (RatingBar)
                layout.findViewById(R.id.rtbEstrelas);


        if (mContato != null) {
            mTextNome.setText(mContato.nome);
            mTextCelular.setText(mContato.celular);
            mRatingEstrelas.setRating(mContato.estrelas);
        }

        return layout;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_hotel_detalhe, menu);

        MenuItem shareItem = menu.findItem(R.id.action_share);

        mShareActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(shareItem);


        String texto = getString(R.string.texto_compartilhar,
                mContato.nome, mContato.estrelas);


        Intent it = new Intent(Intent.ACTION_SEND);
        it.setType("text/plain");
        it.putExtra(Intent.EXTRA_TEXT, texto);
        mShareActionProvider.setShareIntent(it);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_salvar:

                Toast.makeText(getContext(),"fui clicado",Toast.LENGTH_SHORT).show();
                /*
                GenericDialogFragment dialog = GenericDialogFragment.novoDialog(
                        // Id do dialog
                        0,
                        // título
                        R.string.sobre_titulo,
                        // mensagem
                        R.string.sobre_mensagem,
                        // texto dos botões
                        new int[]{
                                android.R.string.ok, // String do Android
                                R.string.sobre_botao_site
                        });
*/
                break;

            case R.id.action_contatos:

                Intent it = new Intent(getContext(),HotelActivity.class);

                startActivity(it);
                getActivity().finish();

                Toast.makeText(getContext(),"fui pra lista de contatos",Toast.LENGTH_SHORT).show();

                /*
                GenericDialogFragment dialog = GenericDialogFragment.novoDialog(
                        // Id do dialog
                        0,
                        // título
                        R.string.sobre_titulo,
                        // mensagem
                        R.string.sobre_mensagem,
                        // texto dos botões
                        new int[]{
                                android.R.string.ok, // String do Android
                                R.string.sobre_botao_site
                        });
*/
                break;


        }

        return super.onOptionsItemSelected(item);
    }

}
