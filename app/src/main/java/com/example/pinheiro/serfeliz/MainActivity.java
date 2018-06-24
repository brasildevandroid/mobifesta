package com.example.pinheiro.serfeliz;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*

        //[verificar se é um novo usuário ou se já é cadastrado]
        FirebaseUserMetadata metadata = auth.getCurrentUser().getMetadata();
        if (metadata.getCreationTimestamp() == metadata.getLastSignInTimestamp()) {
            // The user is new, show them a fancy intro screen!
        } else {
            // This is an existing user, show them a welcome back screen.
        }
*/
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            // already signed in
        } else {
            // not signed in
        }


    }


    @Override

    public void onClick(View view) {

        //[btn sair ]
        if (view.getId() == R.id.sign_out) {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                // user is now signed out
                                startActivity(new Intent(MainActivity.this, TelaLogin.class));
                                finish();

                            }


                        }
                    });
        }
    }

}


