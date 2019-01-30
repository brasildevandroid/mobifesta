package com.example.pinheiro.serfeliz.bancointerno;

import java.util.List;

public class Usuario {
	private String nome;
	private String email;
	private String senha;
	private String dataFestaRegressiva;
	private String orcamento;
	private String mensagem;
	private String  confirmados;

	public String getConfirmados() {
		return confirmados;
	}

	public void setConfirmados(String  confirmados) {
		this.confirmados = confirmados;
	}

	private long id;



    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }


    public String getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(String orcamento) {
        this.orcamento = orcamento;
    }


	public String getDataFestaRegressiva() {
		return dataFestaRegressiva;
	}

	public void setDataFestaRegressiva(String dataFestaRegressiva) {
		this.dataFestaRegressiva = dataFestaRegressiva;
	}

	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
