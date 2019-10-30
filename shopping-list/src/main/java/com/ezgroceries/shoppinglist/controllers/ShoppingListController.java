package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.model.Cocktail;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.services.ShoppingListService;
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

    private final ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingList createShoppingList(@RequestBody ShoppingList newShoppingList) {
        return shoppingListService.create(newShoppingList);
    }

    @PostMapping("/{shoppingListId}/cocktails")
    public void addCocktails(@PathVariable UUID shoppingListId, @RequestBody List<Cocktail> cocktails) {
        shoppingListService.addCocktailsToShoppingList(shoppingListId, cocktails);
    }

    @GetMapping("/{shoppingListId}")
    public ShoppingList getShoppingList(@PathVariable UUID shoppingListId) {
        return shoppingListService.findShoppingListById(shoppingListId);
    }

    @GetMapping
    public List<ShoppingList> getShoppingLists() {
        return shoppingListService.findAll();
    }
}
