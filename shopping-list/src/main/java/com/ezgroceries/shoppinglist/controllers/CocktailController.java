package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.clients.CocktailDBClient;
import com.ezgroceries.shoppinglist.clients.CocktailDBResponse;
import com.ezgroceries.shoppinglist.model.Cocktail;
import com.ezgroceries.shoppinglist.services.CocktailService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jd70695 - Wouter Stinckens
 * @since UOC201910
 */
@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {

    private final CocktailDBClient cocktailDBClient;
    private final CocktailService cocktailService;

    CocktailController(CocktailDBClient cocktailDBClient, CocktailService cocktailService) {
        this.cocktailDBClient = cocktailDBClient;
        this.cocktailService = cocktailService;
    }

    @GetMapping
    public List<Cocktail> get(@RequestParam String search) {
        CocktailDBResponse dbResponse = cocktailDBClient.searchCocktails(search);
        return cocktailService.mergeCocktails(dbResponse);
    }
}