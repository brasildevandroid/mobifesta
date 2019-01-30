package com.example.pinheiro.serfeliz.bancointerno;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class ListUsersActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_list_users);
		
		BD bd = new BD(this);
		
		List<Usuario> list = bd.buscar();

		Usuario user=  (Usuario) list.get(0);
		String nome = String.valueOf(list.size());

		Log.d("lista","user" + user.getNome());

		setListAdapter(new UsuarioAdapter(this, list));


	}
}
