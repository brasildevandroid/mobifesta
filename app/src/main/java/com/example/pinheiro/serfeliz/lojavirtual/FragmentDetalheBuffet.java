package com.example.pinheiro.serfeliz.lojavirtual;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pinheiro.serfeliz.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalheBuffet extends Fragment {


    public FragmentDetalheBuffet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalhe_buffet,container,false);




        return view;



    }

}
