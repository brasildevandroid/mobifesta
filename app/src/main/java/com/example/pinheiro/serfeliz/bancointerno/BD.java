package com.example.pinheiro.serfeliz.bancointerno;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BD {
	private SQLiteDatabase bd;
	
	public BD(Context context){
		BDCore auxBd = new BDCore(context);
		bd = auxBd.getWritableDatabase();
	}
	
	
	public void inserir(Usuario usuario){
		ContentValues valores = new ContentValues();
		valores.put("nome", usuario.getNome());
		valores.put("orcamento", usuario.getOrcamento());
		valores.put("dataFestaRegressiva", usuario.getDataFestaRegressiva());
		valores.put("mensagem", usuario.getMensagem());
		valores.put("confirmados",usuario.getConfirmados());

		bd.insert("usuario", null, valores);

	}
	
	
	public void atualizar(Usuario usuario){
		ContentValues valores = new ContentValues();
		valores.put("nome", usuario.getNome());
		valores.put("orcamento", usuario.getOrcamento());
		valores.put("dataFestaRegressiva", usuario.getDataFestaRegressiva());
		valores.put("mensagem", usuario.getMensagem());
		valores.put("confirmados",usuario.getConfirmados());

		bd.update("usuario", valores, "_id = ?", new String[]{""+usuario.getId()});
	}
	
	
	public void deletar(Usuario usuario){
		bd.delete("usuario", "_id = "+usuario.getId(), null);
	}
	
	
	public List<Usuario> buscar(){
		List<Usuario> list = new ArrayList<Usuario>();
		String[] colunas = new String[]{"_id", "nome", "orcamento","dataFestaRegressiva","mensagem","confirmados"};
		
		Cursor cursor = bd.query("usuario", colunas, null, null, null, null, "nome ASC");
		
		if(cursor.getCount() > 0){
			cursor.moveToFirst();
			
			do{
				
				Usuario u = new Usuario();
				u.setId(cursor.getLong(0));
				u.setNome(cursor.getString(1));
				u.setOrcamento(cursor.getString(2));
				u.setDataFestaRegressiva(cursor.getString(3));
				u.setMensagem(cursor.getString(4));
				u.setConfirmados(cursor.getString(5));
				list.add(u);
				
			}while(cursor.moveToNext());
		}
		
		return(list);
	}
}
