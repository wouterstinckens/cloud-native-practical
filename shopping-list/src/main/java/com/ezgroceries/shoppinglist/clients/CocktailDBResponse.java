package com.ezgroceries.shoppinglist.clients;

import java.util.List;

/**
 * @author jd70695 - Wouter Stinckens
 * @since UOC201910
 */

    public class CocktailDBResponse {

    private List<CocktailDBResource> drinks;

    public List<CocktailDBResource> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<CocktailDBResource> drinks) {
        this.drinks = drinks;
    }

}
   