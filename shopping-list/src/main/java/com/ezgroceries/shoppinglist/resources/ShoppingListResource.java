package com.ezgroceries.shoppinglist.resources;

import java.util.UUID;

/**
 * @author jd70695 - Wouter Stinckens
 * @since UOC201910
 */
public class ShoppingListResource {

    private UUID shoppingListId;
    private String name;

    public ShoppingListResource(UUID shoppingListId, String name) {
        this.shoppingListId = shoppingListId;
        this.name = name;
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
}
