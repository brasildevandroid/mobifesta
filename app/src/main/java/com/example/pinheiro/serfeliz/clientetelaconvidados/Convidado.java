package com.example.pinheiro.serfeliz.clientetelaconvidados;

import java.io.Serializable;

class Convidado implements Serializable {

    String nome;
    String celularConvidado;
    String qtdeAcompanhantesAdultos;
    String qtdeAcompanhantesCriancas;
    String tipoConvidado;
    String status;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Convidado(){


    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String  getCelularConvidado() {
        return celularConvidado;
    }

    public void setCelularConvidado(String celularConvidado) {
        this.celularConvidado = celularConvidado;
    }

    public String getQtdeAcompanhantesAdultos() {
        return qtdeAcompanhantesAdultos;
    }

    public void setAcompanhantesAdultos(String qtdeAcompanhantesAdultos) {
        this.qtdeAcompanhantesAdultos = qtdeAcompanhantesAdultos;
    }

    public String getQtdeAcompanhantesCriancas() {

        return qtdeAcompanhantesCriancas;
    }

    public void setAcompanhantesCriancas(String getQtdeAcompanhantesCriancas) {
        this.qtdeAcompanhantesCriancas = getQtdeAcompanhantesCriancas;
    }

    public String getTipoConvidado() {
        return tipoConvidado;
    }

    public void setTipoConvidado(String tipoConvidado) {
        this.tipoConvidado = tipoConvidado;
    }
}


