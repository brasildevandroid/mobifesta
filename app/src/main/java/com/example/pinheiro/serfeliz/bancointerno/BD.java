package com.example.pinheiro.serfeliz.bancointerno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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

	/*

	public void inserirImagens(Usuario usuario,String x){

		try{
			FileInputStream fs = new FileInputStream(x);

			byte[] imgByte = new byte[fs.available()];
			fs.read(imgByte);
			ContentValues valores = new ContentValues();
			valores.put("imgCapa",imgByte);

			bd.update("usuario", valores, "_id = ?", new String[]{""+usuario.getId()});
			fs.close();


		}catch (IOException e){
			e.printStackTrace();
		}


	}

	*/
	
	public void atualizar(Usuario usuario){
		ContentValues valores = new ContentValues();
		valores.put("nome", usuario.getNome());
		valores.put("orcamento", usuario.getOrcamento());
		valores.put("dataFestaRegressiva", usuario.getDataFestaRegressiva());
		valores.put("mensagem", usuario.getMensagem());
		valores.put("confirmados",usuario.getConfirmados());
		valores.put("imgCapa",usuario.getImgCapa());



		bd.update("usuario", valores, "_id = ?", new String[]{"" + usuario.getId()});
	}
	
	
	public void deletar(Usuario usuario){
		bd.delete("usuario", "_id = " + usuario.getId(), null);
	}
	
	
	public List<Usuario> buscar(){
		List<Usuario> list = new ArrayList<Usuario>();
		String[] colunas = new String[]{"_id", "nome", "orcamento","dataFestaRegressiva","mensagem","confirmados","imgCapa"};
		
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
				u.setImgCapa(cursor.getBlob(6));


				list.add(u);
				
			}while(cursor.moveToNext());
		}
		
		return(list);
	}
}
