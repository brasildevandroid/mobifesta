/**
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 package com.example.pinheiro.serfeliz.lojavirtual;

import com.google.firebase.firestore.IgnoreExtraProperties;

/**
 * Produto POJO.
 */
@IgnoreExtraProperties
public class Produto {

    public static final String FIELD_CITY = "city";
    public static final String FIELD_CATEGORY = "category";
    public static final String FIELD_PRICE = "price";
    public static final String FIELD_POPULARITY = "numRatings";
    public static final String FIELD_AVG_RATING = "avgRating";

    private String nome;
    private String cidade;
    private String categoria;
    private String foto;
    private String bairro;
    private int preco;
    private int numRatings;
    private double avgRating;

    public Produto() {}

    public Produto(String nome, String cidade, String  categoria, String foto,String bairro,
                   int preco, int numRatings, double avgRating) {
        this.nome = nome;
        this.cidade = cidade;
        this.categoria = categoria;
        this.preco = preco;
        this.foto = foto;
        this.bairro = bairro;
        this.numRatings = numRatings;
        this.avgRating = avgRating;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCidade()
    {
        return cidade;
    }

    public void setCity(String cidade) {
        this.cidade = cidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }

    public String getFoto() {
        return foto;
    }

    public void setPhoto(String foto) {
        this.foto = foto;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }
}
