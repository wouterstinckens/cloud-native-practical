package com.ezgroceries.shoppinglist.controllers.model;

import java.util.Set;
import java.util.UUID;

/**
 * @author jd70695 - Wouter Stinckens
 * @since UOC201910
 */
public class CocktailDTO {

    private final UUID id;
    private final String drinkId;
    private final String name;
    private final String glass;
    private final String instructions;
    private final String image;
    private final Set<String> ingredients;

    public CocktailDTO(UUID id, String drinkId, String name, String glass, String instructions, String image, Set<String> ingredients) {
        this.id = id;
        this.drinkId = drinkId;
        this.name = name;
        this.glass = glass;
        this.instructions = instructions;
        this.image = image;
        this.ingredients = ingredients;
    }

    public UUID getId() {
        return id;
    }

    public String getDrinkId() {
        return drinkId;
    }

    public String getName() {
        return name;
    }

    public String getGlass() {
        return glass;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getImage() {
        return image;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }
}
