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
import android.text.TextUtils;

import com.example.pinheiro.serfeliz.R;
import com.google.firebase.firestore.Query;

/**
 * Object for passing filters around.
 */
public class Filters {

    private String categoria = null;
    private String cidade = null;
    private int preco = -1;
    private String relevancia = null;
    private Query.Direction sortDirection = null;

    public Filters() {}

    public static Filters getDefault() {
        Filters filters = new Filters();
        filters.setSortBy(Restaurant.FIELD_AVG_RATING);
        filters.setSortDirection(Query.Direction.DESCENDING);

        return filters;
    }

    public boolean hasCategory() {
        return !(TextUtils.isEmpty(categoria));
    }

    public boolean hasCity() {
        return !(TextUtils.isEmpty(cidade));
    }

    public boolean hasPrice() {
        return (preco > 0);
    }

    public boolean hasSortBy() {
        return !(TextUtils.isEmpty(relevancia));
    }

    public String getCategory() {
        return categoria;
    }

    public void setCategory(String category) {
        this.categoria = category;
    }

    public String getCity() {
        return cidade;
    }

    public void setCity(String city) {
        this.cidade = city;
    }

    public int getPrice() {
        return preco;
    }

    public void setPrice(int price) {
        this.preco = preco;
    }

    public String getSortBy() {
        return relevancia;
    }

    public void setSortBy(String sortBy) {
        this.relevancia = sortBy;
    }

    public Query.Direction getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(Query.Direction sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getSearchDescription(Context context) {
        StringBuilder desc = new StringBuilder();

        if (categoria == null && cidade == null) {
            desc.append("<b>");
            desc.append(context.getString(R.string.all_restaurants));
            desc.append("</b>");
        }

        if (categoria != null) {
            desc.append("<b>");
            desc.append(categoria);
            desc.append("</b>");
        }

        if (categoria != null && cidade != null) {
            desc.append(" in ");
        }

        if (cidade != null) {
            desc.append("<b>");
            desc.append(cidade);
            desc.append("</b>");
        }

        if (preco > 0) {
            desc.append(" for ");
            desc.append("<b>");
            desc.append(RestaurantUtil.getPriceString(preco));
            desc.append("</b>");
        }

        return desc.toString();
    }

    public String getOrderDescription(Context context) {
        if (Restaurant.FIELD_PRICE.equals(relevancia)) {
            return context.getString(R.string.sorted_by_price);
        } else if (Restaurant.FIELD_POPULARITY.equals(relevancia)) {
            return context.getString(R.string.sorted_by_popularity);
        } else {
            return context.getString(R.string.sorted_by_rating);
        }
    }
}
