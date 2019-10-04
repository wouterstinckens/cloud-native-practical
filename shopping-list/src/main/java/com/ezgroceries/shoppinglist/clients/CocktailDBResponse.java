package com.ezgroceries.shoppinglist.clients;

import com.ezgroceries.shoppinglist.resources.DrinkResource;
import java.util.List;

/**
 * @author jd70695 - Wouter Stinckens
 * @since UOC201910
 */

    public class CocktailDBResponse {

    private List<DrinkResource> drinks;

    public List<DrinkResource> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkResource> drinks) {
        this.drinks = drinks;
    }

}
   