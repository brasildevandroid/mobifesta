package com.example.pinheiro.serfeliz.lojavirtual;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pinheiro.serfeliz.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentLojaVirtual extends Fragment {


    private FirebaseFirestore mFirestore;
    private Query mQuery;

    RecyclerView mRestaurantsRecycler;
    private static final String TAG = "MainActivity";
    private static final int RC_SIGN_IN = 9001;
    private static final int LIMIT = 50;
    Button btnAddRestaurant;


    private FilterDialogFragment mFilterDialog;
    private RestaurantAdapter mAdapter;

    private MainActivityViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.loja_virtual,container,false);

        mRestaurantsRecycler = (RecyclerView)view.findViewById(R.id.rec_Restaurantes);

    //    ButterKnife.bind(getActivity());
        btnAddRestaurant = (Button) view.findViewById(R.id.btnAdd);

        initRecyclerView();


        // View model
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        // Enable Firestore logging
        FirebaseFirestore.setLoggingEnabled(true);

        // Filter Dialog
        mFilterDialog = new FilterDialogFragment();



        btnAddRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onAddItemsClicked();

            }
        });


        initFirestore();





        return view;


    }

    private void initRecyclerView() {
        if (mQuery == null) {
            Log.w(TAG, "No query, not initializing RecyclerView");
        }

        mAdapter = new RestaurantAdapter(mQuery,null) {

            @Override
            protected void onDataChanged() {

                // Show/hide content if the query returns empty.
                if (getItemCount() == 0) {
                    mRestaurantsRecycler.setVisibility(View.GONE);
                   // mEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mRestaurantsRecycler.setVisibility(View.VISIBLE);
                   // mEmptyView.setVisibility(View.GONE);
                }
            }

            @Override
            protected void onError(FirebaseFirestoreException e) {
                // Show a snackbar on errors
                /*
                Snackbar.make(findViewById(android.R.id.content),
                        "Error: check logs for info.", Snackbar.LENGTH_LONG).show();
                        */
            }
        };

        mRestaurantsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRestaurantsRecycler.setAdapter(mAdapter);
    }

    // inicia o firestore da loja virtual


    private void initFirestore() {


        mFirestore = FirebaseFirestore.getInstance ();
        // TODO(developer): Implement


        // obter os 50 melhores restaurantes avaliados
        mQuery = mFirestore.collection("restaurants")
                .orderBy("avgRating", Query.Direction.DESCENDING)
                .limit(LIMIT);


    }

    private void onAddItemsClicked() {
        // Get a reference to the restaurants collection
        CollectionReference restaurants = mFirestore.collection("restaurants");

        for (int i = 0; i < 10; i++) {
            // Get a random Restaurant POJO
            Restaurant restaurant = RestaurantUtil.getRandom(getContext());

            // Add a new document to the restaurants collection
            restaurants.add(restaurant);
        }
    }




}
