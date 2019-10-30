package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.controllers.model.CocktailDTO;
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

    private final CocktailService cocktailService;

    CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping
    public List<CocktailDTO> get(@RequestParam String search) {
        return cocktailService.searchCocktails(search);
    }
}