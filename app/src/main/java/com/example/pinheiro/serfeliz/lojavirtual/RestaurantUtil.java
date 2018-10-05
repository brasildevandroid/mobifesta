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

import android.content.Context;


import com.example.pinheiro.serfeliz.R;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Utilities for Restaurants.
 */
public class RestaurantUtil {

    private static final String TAG = "RestaurantUtil";

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(2, 4, 60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    private static final String RESTAURANT_URL_FMT = "https://storage.googleapis.com/firestorequickstarts.appspot.com/food_%d.png";

    private static final int MAX_IMAGE_NUM = 22;

    private static final String[] NAME_FIRST_WORDS = {

            "Ana Julia",
            "Marcos",
            "Viviane Salgados",
            "Tuanne Lembranças",
            "Pablo Anima",
            "L&M Festas",
            "Maria Bolo",

    };

    private static final String[] NAME_SECOND_WORDS = {
            "Ana Mania",
            "Celler Buffet",
            "Lina Animação",
            "Garçons de Preto",
            "Familia Gastrônomica",
            "Transporte Moacir",
            "Vitoria Pula-Pula",
    };


    /**
     * Create a random Produto POJO.
     */
    public static Produto getRandom(Context context) {
        Produto produto = new Produto();
        Random random = new Random();

        // Cities (first elemnt is 'Any')
        String[] cities = context.getResources().getStringArray(R.array.cities);
        cities = Arrays.copyOfRange(cities, 1, cities.length);

        // Categories (first element is 'Any')
        String[] categories = context.getResources().getStringArray(R.array.categories);
        categories = Arrays.copyOfRange(categories, 1, categories.length);

        int[] prices = new int[]{1, 2, 3};

        produto.setNome(getRandomName(random));
        produto.setCity(getRandomString(cities, random));
        produto.setCategoria(getRandomString(categories, random));
        produto.setPhoto(getRandomImageUrl(random));
        produto.setPreco(getRandomInt(prices, random));
        produto.setAvgRating(getRandomRating(random));
        produto.setNumRatings(random.nextInt(20));

        return produto;
    }


    /**
     * Get a random image.
     */
    private static String getRandomImageUrl(Random random) {
        // Integer between 1 and MAX_IMAGE_NUM (inclusive)
        int id = random.nextInt(MAX_IMAGE_NUM) + 1;

        return String.format(Locale.getDefault(), RESTAURANT_URL_FMT, id);
    }

    /**
     * Get price represented as dollar signs.
     */
    public static String getPriceString(Produto produto) {
        return getPriceString(produto.getPreco());
    }

    /**
     * Get price represented as dollar signs.
     */
    public static String getPriceString(int priceInt) {
        switch (priceInt) {
            case 1:
                return "R$ 3.000,00";
            case 2:
                return "R$ 1.800,00";
            case 3:
            default:
                return "R$ 500,00";
        }
    }

    private static double getRandomRating(Random random) {
        double min = 1.0;
        return min + (random.nextDouble() * 4.0);
    }

    private static String getRandomName(Random random) {
        return getRandomString(NAME_FIRST_WORDS, random) + " "
                + getRandomString(NAME_SECOND_WORDS, random);
    }

    private static String getRandomString(String[] array, Random random) {
        int ind = random.nextInt(array.length);
        return array[ind];
    }

    private static int getRandomInt(int[] array, Random random) {
        int ind = random.nextInt(array.length);
        return array[ind];
    }

}
