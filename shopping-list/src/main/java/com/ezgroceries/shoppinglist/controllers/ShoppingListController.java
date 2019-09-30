package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.resources.CocktailResource;
import com.ezgroceries.shoppinglist.resources.ShoppingListResource;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
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
        return new ShoppingListResource(UUID.fromString("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"), newShoppingList.getName());
    }

    @PostMapping("/{shoppingListId}/cocktails")
    public List<CocktailResource> addCocktails(@PathVariable UUID shoppingListId, @RequestBody List<CocktailResource> cocktails) {
        return cocktails;
    }
}
