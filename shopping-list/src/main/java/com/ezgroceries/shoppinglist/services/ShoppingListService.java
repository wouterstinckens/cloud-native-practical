package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.model.Cocktail;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.repositories.CocktailRepository;
import com.ezgroceries.shoppinglist.repositories.ShoppingListRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * @author jd70695 - Wouter Stinckens
 * @since UOC201910
 */
@Service
public class ShoppingListService {

    private final CocktailRepository cocktailRepository;
    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(CocktailRepository cocktailRepository, ShoppingListRepository shoppingListRepository) {
        this.cocktailRepository = cocktailRepository;
        this.shoppingListRepository = shoppingListRepository;
    }

    public ShoppingList create(ShoppingList shoppingListResource) {
        ShoppingListEntity entity = new ShoppingListEntity();
        entity.setName(shoppingListResource.getName());
        shoppingListRepository.save(entity);

        shoppingListResource.setShoppingListId(entity.getId());
        return shoppingListResource;
    }

    public void addCocktailsToShoppingList(UUID shoppingListId, List<Cocktail> cocktails) {
        List<CocktailEntity> cocktailEntities =
                (List<CocktailEntity>) cocktailRepository.findAllById(cocktails.stream().map(Cocktail::getId).collect(Collectors.toList()));
        shoppingListRepository.findById(shoppingListId).ifPresent(shoppingListEntity -> {
            shoppingListEntity.getCocktails().addAll(cocktailEntities);
            shoppingListRepository.save(shoppingListEntity);
        });
    }

    public ShoppingList findShoppingListById(UUID shoppingListId) {
        return shoppingListRepository.findById(shoppingListId)
                .map(shoppingListEntity -> new ShoppingList(shoppingListEntity.getId(), shoppingListEntity.getName(),
                        shoppingListEntity.getCocktails().stream().flatMap(
                                cocktailEntity -> cocktailEntity.getIngredients().stream()).collect(Collectors.toSet())))
                .orElseThrow(() -> new Error("No shopping list found with UUID: " + shoppingListId));
    }

    public List<ShoppingList> findAll() {
        List<ShoppingListEntity> entities = (List<ShoppingListEntity>) shoppingListRepository.findAll();
        return entities.stream()
                .map(shoppingListEntity -> new ShoppingList(shoppingListEntity.getId(), shoppingListEntity.getName(),
                        shoppingListEntity.getCocktails().stream().flatMap(
                                cocktailEntity -> cocktailEntity.getIngredients().stream()).collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }
}
