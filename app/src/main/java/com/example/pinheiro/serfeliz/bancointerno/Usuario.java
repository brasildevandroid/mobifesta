package com.example.pinheiro.serfeliz.bancointerno;

public class Usuario {

	private String nome;
	private String email;
	private String dataFestaRegressiva;
	private String orcamento;
	private String mensagem;
	private String  confirmados;
	private long id;
	private byte[] imgCapa;

	public byte[] getImgCapa() {
		return imgCapa;
	}

	public void setImgCapa(byte[] imgCapa) {
		this.imgCapa = imgCapa;
	}

	public String getConfirmados() {
		return confirmados;
	}

	public void setConfirmados(String  confirmados) {
		this.confirmados = confirmados;
	}



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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
