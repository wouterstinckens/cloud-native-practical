package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.clients.CocktailDBClient;
import com.ezgroceries.shoppinglist.clients.CocktailDBResource;
import com.ezgroceries.shoppinglist.clients.CocktailDBResponse;
import com.ezgroceries.shoppinglist.controllers.model.CocktailDTO;
import com.ezgroceries.shoppinglist.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.repositories.CocktailRepository;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

/**
 * @author jd70695 - Wouter Stinckens
 * @since UOC201910
 */
@Service
public class CocktailService {

    private final CocktailDBClient cocktailDBClient;
    private final CocktailRepository cocktailRepository;

    public CocktailService(CocktailDBClient cocktailDBClient, CocktailRepository cocktailRepository) {
        this.cocktailDBClient = cocktailDBClient;
        this.cocktailRepository = cocktailRepository;
    }

    public List<CocktailDTO> searchCocktails(String search) {
        CocktailDBResponse dbResponse = cocktailDBClient.searchCocktails(search);
        return mergeCocktails(dbResponse);
    }

    public List<CocktailDTO> mergeCocktails(CocktailDBResponse cocktailResources) {
        //Get all the idDrink attributes
        List<String> ids = cocktailResources.getDrinks().stream().map(CocktailDBResource::getIdDrink).collect(Collectors.toList());

        //Get all the ones we already have from our DB, use a Map for convenient lookup
        Map<String, CocktailEntity> existingEntityMap = cocktailRepository.findByIdDrinkIn(ids)
                .stream().collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        //Stream over all the drinks, map them to the existing ones, persist a new one if not existing
        Map<String, CocktailEntity> allEntityMap = cocktailResources.getDrinks().stream().map(drinkResource -> {
            CocktailEntity cocktailEntity = existingEntityMap.get(drinkResource.getIdDrink());
            if (cocktailEntity == null) {
                CocktailEntity newCocktailEntity = new CocktailEntity();
                newCocktailEntity.setIdDrink(drinkResource.getIdDrink());
                newCocktailEntity.setName(drinkResource.getStrDrink());
                newCocktailEntity.setIngredients(getIngredients(drinkResource));
                cocktailEntity = cocktailRepository.save(newCocktailEntity);
            }
            return cocktailEntity;
        }).collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        //Merge drinks and our entities, transform to CocktailResource instances
        return mergeAndTransform(cocktailResources.getDrinks(), allEntityMap);
    }

    private List<CocktailDTO> mergeAndTransform(List<CocktailDBResource> cocktails, Map<String, CocktailEntity> allEntityMap) {
        return cocktails.stream().map(drinkResource -> new CocktailDTO(allEntityMap.get(drinkResource.getIdDrink()).getId(), drinkResource.getIdDrink(),
                drinkResource.getStrDrink(), drinkResource.getStrGlass(), drinkResource.getStrInstructions(), drinkResource.getStrDrinkThumb(),
                getIngredients(drinkResource))).collect(Collectors.toList());
    }

    private Set<String> getIngredients(CocktailDBResource drink) {
        return Stream.of(
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
        ).filter(Objects::nonNull).collect(Collectors.toSet());
    }



}
