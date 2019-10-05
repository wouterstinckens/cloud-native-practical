package com.ezgroceries.shoppinglist.resources;

import java.util.List;

/**
 * @author jd70695 - Wouter Stinckens
 * @since UOC201910
 */
public class CocktailResource {

    private final String cocktailId;
    private final String name;
    private final String glass;
    private final String instructions;
    private final String image;
    private final List<String> ingredients;

    public CocktailResource(String cocktailId, String name, String glass, String instructions, String image, List<String> ingredients) {
        this.cocktailId = cocktailId;
        this.name = name;
        this.glass = glass;
        this.instructions = instructions;
        this.image = image;
        this.ingredients = ingredients;
    }

    public String getCocktailId() {
        return cocktailId;
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

    public List<String> getIngredients() {
        return ingredients;
    }
}
