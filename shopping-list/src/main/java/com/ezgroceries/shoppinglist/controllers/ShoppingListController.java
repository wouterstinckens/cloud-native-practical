package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.resources.CocktailResource;
import com.ezgroceries.shoppinglist.resources.ShoppingListResource;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jd70695 - Wouter Stinckens
 * @since UOC201910
 */
@RestController
@RequestMapping("/shopping-lists")
public class ShoppingListController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResource createShoppingList(@RequestBody ShoppingListResource newShoppingList) {
        return new ShoppingListResource(
                UUID.fromString("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"), newShoppingList.getName(), null);
    }

    @PostMapping("/{shoppingListId}/cocktails")
    public List<CocktailResource> addCocktails(@PathVariable UUID shoppingListId, @RequestBody List<CocktailResource> cocktails) {
        return cocktails;
    }

    @GetMapping("/{shoppingListId}")
    public ShoppingListResource getShoppingList(@PathVariable String shoppingListId) {
        return new ShoppingListResource(UUID.fromString("90689338-499a-4c49-af90-f1e73068ad4f"),
                "Stephanie's birthday",
                Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"));
    }

    @GetMapping
    public List<ShoppingListResource> getShoppingLists() {
        return Arrays.asList(
                new ShoppingListResource(UUID.fromString("4ba92a46-1d1b-4e52-8e38-13cd56c7224c"),
                        "Stephanie's birthday",
                        Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao")),
                new ShoppingListResource(UUID.fromString("6c7d09c2-8a25-4d54-a979-25ae779d2465"),
                        "My Birthday",
                        Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"))
        );
    }
}
