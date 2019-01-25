package com.example.pinheiro.serfeliz.ex16_fragments;

import java.io.Serializable;

public class Contato implements Serializable {
    public String nome;
    public String celular;
    public float estrelas;

    public Contato(String nome, String numero, float estrelas) {
        this.nome = nome;
        this.celular = numero;
        this.estrelas = estrelas;
    }

    @Override
    public String toString() {
        return nome;
    }
}

