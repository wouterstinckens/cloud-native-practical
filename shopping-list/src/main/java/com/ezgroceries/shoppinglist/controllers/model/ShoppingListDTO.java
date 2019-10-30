package com.ezgroceries.shoppinglist.controllers.model;

import java.util.Set;
import java.util.UUID;

/**
 * @author jd70695 - Wouter Stinckens
 * @since UOC201910
 */
public class ShoppingListDTO {

    private UUID shoppingListId;
    private String name;
    private Set<String> ingredients;

    public ShoppingListDTO(UUID shoppingListId, String name, Set<String> ingredients) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.ingredients = ingredients;
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }
}
