package com.example.pinheiro.serfeliz.lojavirtual;

class Buffet {

    String capa;
    String foto;
    String descricao;
    String nomeFornecedor;
    String bairroFornecedor;
    String convidadosMinimo;
    String convidadosMaximo;
    String preco;
    double numRatings;


    public double getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(double numRatings) {
        this.numRatings = numRatings;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }




    public Buffet(){


    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getBairroFornecedor() {
        return bairroFornecedor;
    }

    public void setBairroFornecedor(String bairroFornecedor) {
        this.bairroFornecedor = bairroFornecedor;
    }

    public String getConvidadosMinimo() {
        return convidadosMinimo;
    }

    public void setConvidadosMinimo(String convidadosMinimo) {
        this.convidadosMinimo = convidadosMinimo;
    }

    public String getConvidadosMaximo() {
        return convidadosMaximo;
    }

    public void setConvidadosMaximo(String convidadosMaximo) {
        this.convidadosMaximo = convidadosMaximo;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}
