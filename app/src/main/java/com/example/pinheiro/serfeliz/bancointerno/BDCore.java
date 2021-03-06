package com.example.pinheiro.serfeliz.bancointerno;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDCore extends SQLiteOpenHelper {
	private static final String NOME_BD = "teste";
	private static final int VERSAO_BD = 7;
	
	
	public BDCore(Context ctx){
		super(ctx, NOME_BD, null, VERSAO_BD);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase bd) {
		bd.execSQL("create table usuario(_id integer primary key autoincrement, nome text not null, " +
				"orcamento text not null,dataFestaRegressiva text not null,mensagem text not null,confirmados text not null,imgCapa blob,qtdeAnotacoes text,calculadoraAdultos text,calculadoraCriancas text,calculadoraBolo text,calculadoraDocinhos text,calculadoraSalgadinhos text,calculadoraSanduiches text,calculadoraRefrigerante text,calculadoraSuco text,calculadoraAgua text,calculadoraPratinhos text,calculadoraCopos text,calculadoraColheres text,calculadoraGarfos text,calculadoraGuardanapos text,convidadosAdultos text,convidadosCriancas text,calculadoraCerveja text);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
		bd.execSQL("drop table usuario;");
		onCreate(bd);
	}



}
