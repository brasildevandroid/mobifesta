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
        valores.put("imgCapa",usuario.getImgCapa());
        valores.put("qtdeAnotacoes",usuario.getQtdeAnotacoes());
        valores.put("calculadoraAdultos",usuario.getCalculadoraAdultos());
        valores.put("calculadoraCriancas",usuario.getCalculadoraCriancas());
        valores.put("calculadoraBolo",usuario.getCalculadoraBolo());
        valores.put("calculadoraDocinhos",usuario.getCalculadoraDocinhos());
        valores.put("calculadoraSalgadinhos",usuario.getCalculadoraSalgadinhos());
        valores.put("calculadoraSanduiches",usuario.getCalculadoraSanduiches());
        valores.put("calculadoraRefrigerante",usuario.getCalculadoraRefrigerante());
        valores.put("calculadoraSuco",usuario.getCalculadoraSuco());
        valores.put("calculadoraAgua",usuario.getCalculadoraAgua());
		valores.put("calculadoraCerveja",usuario.getCalculadoraCerveja());
        valores.put("calculadoraPratinhos",usuario.getCalculadoraPratinhos());
        valores.put("calculadoraCopos",usuario.getCalculadoraCopos());
        valores.put("calculadoraColheres",usuario.getCalculadoraColheres());
        valores.put("calculadoraGarfos",usuario.getCalculadoraGarfos());
        valores.put("calculadoraGuardanapos",usuario.getCalculadoraGuardanapos());
		valores.put("convidadosAdultos",usuario.getConvidadosAdultos());
		valores.put("convidadosCriancas",usuario.getConvidadosCriancas());

		bd.insert("usuario", null, valores);

	}

	
	public void atualizar(Usuario usuario){

		ContentValues valores = new ContentValues();

		valores.put("nome", usuario.getNome());
		valores.put("orcamento", usuario.getOrcamento());
		valores.put("dataFestaRegressiva", usuario.getDataFestaRegressiva());
		valores.put("mensagem", usuario.getMensagem());
		valores.put("confirmados",usuario.getConfirmados());
		valores.put("imgCapa",usuario.getImgCapa());
		valores.put("qtdeAnotacoes",usuario.getQtdeAnotacoes());
		valores.put("calculadoraAdultos",usuario.getCalculadoraAdultos());
		valores.put("calculadoraCriancas",usuario.getCalculadoraCriancas());
        valores.put("calculadoraBolo",usuario.getCalculadoraBolo());
        valores.put("calculadoraDocinhos",usuario.getCalculadoraDocinhos());
        valores.put("calculadoraSalgadinhos",usuario.getCalculadoraSalgadinhos());
        valores.put("calculadoraSanduiches",usuario.getCalculadoraSanduiches());
        valores.put("calculadoraRefrigerante",usuario.getCalculadoraRefrigerante());
        valores.put("calculadoraSuco",usuario.getCalculadoraSuco());
        valores.put("calculadoraAgua",usuario.getCalculadoraAgua());
		valores.put("calculadoraCerveja",usuario.getCalculadoraCerveja());
        valores.put("calculadoraPratinhos",usuario.getCalculadoraPratinhos());
        valores.put("calculadoraCopos",usuario.getCalculadoraCopos());
        valores.put("calculadoraColheres",usuario.getCalculadoraColheres());
        valores.put("calculadoraGarfos",usuario.getCalculadoraGarfos());
        valores.put("calculadoraGuardanapos",usuario.getCalculadoraGuardanapos());
		valores.put("convidadosAdultos",usuario.getConvidadosAdultos());
		valores.put("convidadosCriancas",usuario.getConvidadosCriancas());

		bd.update("usuario", valores, "_id = ?", new String[]{"" + usuario.getId()});
	}
	
	
	public void deletar(Usuario usuario){
		bd.delete("usuario", "_id = " + usuario.getId(), null);
	}
	
	
	public List<Usuario> buscar(){
		List<Usuario> list = new ArrayList<Usuario>();
		String[] colunas = new String[]{"_id", "nome", "orcamento","dataFestaRegressiva","mensagem","confirmados","imgCapa","qtdeAnotacoes","calculadoraAdultos","calculadoraCriancas",
		"calculadoraBolo","calculadoraDocinhos","calculadoraSalgadinhos","calculadoraSanduiches","calculadoraRefrigerante","calculadoraSuco","calculadoraAgua","calculadoraPratinhos","calculadoraCopos","calculadoraColheres","calculadoraGarfos","calculadoraGuardanapos","convidadosAdultos","convidadosCriancas","calculadoraCerveja"};
		
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
				u.setQtdeAnotacoes(cursor.getString(7));
				u.setCalculadoraAdultos(cursor.getString(8));
				u.setCalculadoraCriancas(cursor.getString(9));
                u.setCalculadoraBolo(cursor.getString(10));
                u.setCalculadoraDocinhos(cursor.getString(11));
                u.setCalculadoraSalgadinhos(cursor.getString(12));
                u.setCalculadoraSanduiches(cursor.getString(13));
                u.setCalculadoraRefrigerante(cursor.getString(14));
                u.setCalculadoraSuco(cursor.getString(15));
                u.setCalculadoraAgua(cursor.getString(16));
                u.setCalculadoraPratinhos(cursor.getString(17));
                u.setCalculadoraCopos(cursor.getString(18));
                u.setCalculadoraColheres(cursor.getString(19));
                u.setCalculadoraGarfos(cursor.getString(20));
                u.setCalculadoraGuardanapos(cursor.getString(21));
                u.setConvidadosAdultos(cursor.getString(22));
                u.setConvidadosCriancas(cursor.getString(23));
				u.setCalculadoraCerveja(cursor.getString(24));

				list.add(u);
				
			}while(cursor.moveToNext());
		}
		
		return(list);
	}
}
