package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.clients.CocktailDBClient;
import com.ezgroceries.shoppinglist.clients.CocktailDBResponse;
import com.ezgroceries.shoppinglist.resources.CocktailResource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Qualifier;
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

    CocktailController(@Qualifier("cocktailDBClient") CocktailDBClient cocktailDBClient) {
        this.cocktailDBClient = cocktailDBClient;
    }

    @GetMapping
    public List<CocktailResource> get(@RequestParam String search) {
        CocktailDBResponse dbResponse = cocktailDBClient.searchCocktails(search);

        return dbResponse.getDrinks().stream()
                .map(drink -> new CocktailResource(
                        drink.getIdDrink(),
                        drink.getStrDrink(),
                        drink.getStrGlass(),
                        drink.getStrInstructions(),
                        drink.getStrDrinkThumb(),
                        Stream.of(
                                drink.getStrIngredient1(),
                                drink.getStrIngredient2(),
                                drink.getStrIngredient3(),
                                drink.getStrIngredient4(),
                                drink.getStrIngredient5(),
                                drink.getStrIngredient6(),
                                drink.getStrIngredient7(),
                                drink.getStrIngredient8(),
                                drink.getStrIngredient9(),
                                drink.getStrIngredient10(),
                                drink.getStrIngredient11(),
                                drink.getStrIngredient12(),
                                drink.getStrIngredient13(),
                                drink.getStrIngredient14(),
                                drink.getStrIngredient15()
                        ).filter(Objects::nonNull).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}