package com.example.pinheiro.serfeliz.ex16_fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.pinheiro.serfeliz.R;

public class HotelDialogFragment extends DialogFragment
        implements TextView.OnEditorActionListener {

    private static final String DIALOG_TAG = "editDialog";
    private static final String EXTRA_CONTATO = "contato";

    private EditText txtNome;
    private EditText txtEndereco;
    private RatingBar rtbEstrelas;
    private Contato mContato;

    public static HotelDialogFragment newInstance(Contato contato) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_CONTATO, contato);

        HotelDialogFragment dialog = new HotelDialogFragment();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContato = (Contato)getArguments().getSerializable(EXTRA_CONTATO);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(
                R.layout.fragment_dialog_hotel, container, false);

        txtNome = (EditText) layout.findViewById(R.id.txtNome);
        txtNome.requestFocus();
        txtEndereco = (EditText)layout.findViewById(R.id.txtEndereco);
        txtEndereco.setOnEditorActionListener(this);
        rtbEstrelas = (RatingBar)layout.findViewById(R.id.rtbEstrelas);

        if (mContato != null) {
            txtNome.setText(mContato.nome);
            txtEndereco.setText(mContato.celular);
            rtbEstrelas.setRating(mContato.estrelas);
        }

        // Exibe o teclado virtual ao exibir o Dialog
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        getDialog().setTitle(R.string.acao_novo);

        return layout;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            Activity activity = getActivity();
            if (activity instanceof AoSalvarHotel) {
                if (mContato == null) {
                    mContato = new Contato(
                            txtNome.getText().toString(),
                            txtEndereco.getText().toString(),
                            rtbEstrelas.getRating());
                } else {
                    mContato.nome = txtNome.getText().toString();
                    mContato.celular = txtEndereco.getText().toString();
                    mContato.estrelas = rtbEstrelas.getRating();
                }

                AoSalvarHotel listener = (AoSalvarHotel) activity;
                listener.salvouHotel(mContato);
                // Feche o dialog
                dismiss();
                return true;
            }
        }
        return false;
    }

    public void abrir(FragmentManager fm) {
        if (fm.findFragmentByTag(DIALOG_TAG) == null) {
            show(fm, DIALOG_TAG);
        }
    }

    public interface AoSalvarHotel {
        void salvouHotel(Contato contato);
    }
}

