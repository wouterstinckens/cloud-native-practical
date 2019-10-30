package com.ezgroceries.shoppinglist.clients;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ezgroceries.shoppinglist.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.model.Cocktail;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.repositories.CocktailRepository;
import com.ezgroceries.shoppinglist.repositories.ShoppingListRepository;
import com.ezgroceries.shoppinglist.services.ShoppingListService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jd70695 - Wouter Stinckens
 * @since UOC201910
 */
public class ShoppingListServiceTest {

    private ShoppingListService shoppingListService;
    private CocktailRepository cocktailRepository;
    private ShoppingListRepository shoppingListRepository;

    @Before
    public void before() {
        cocktailRepository = mock(CocktailRepository.class);
        shoppingListRepository = mock(ShoppingListRepository.class);
        shoppingListService = new ShoppingListService(cocktailRepository, shoppingListRepository);
    }

    @Test
    public void testCreate() {
        UUID identifier = UUID.randomUUID();

        ShoppingListEntity entity = new ShoppingListEntity();
        entity.setId(identifier);
        when(shoppingListRepository.save(any())).thenReturn(entity);

        ShoppingList shoppingList = new ShoppingList(null, "My shopping list", null);
        shoppingList = shoppingListService.create(shoppingList);

        assertEquals(identifier, shoppingList.getShoppingListId());
    }

    @Test
    public void testAddCocktailsToShoppingList() {
        UUID cocktailId = UUID.randomUUID();
        CocktailEntity cocktailEntity = new CocktailEntity();
        cocktailEntity.setId(cocktailId);
        when(cocktailRepository.findAllById(any())).thenReturn(Collections.singletonList(cocktailEntity));

        UUID shoppingListID = UUID.randomUUID();
        ShoppingListEntity shoppingListEntity = new ShoppingListEntity();
        shoppingListEntity.setId(shoppingListID);
        shoppingListEntity.setCocktails(new ArrayList<>());
        when(shoppingListRepository.findById(any())).thenReturn(Optional.of(shoppingListEntity));

        Cocktail cocktail = new Cocktail(cocktailId, null, null, null, null, null, null);
        shoppingListService.addCocktailsToShoppingList(shoppingListID, Collections.singletonList(cocktail));

        assertEquals(shoppingListEntity.getCocktails().get(0).getId(), cocktailId);
    }

    @Test
    public void testFindShoppingListById() {
        UUID shoppingListID = UUID.randomUUID();
        ShoppingListEntity shoppingListEntity = new ShoppingListEntity();
        shoppingListEntity.setId(shoppingListID);

        UUID cocktailID = UUID.randomUUID();
        CocktailEntity cocktailEntity = new CocktailEntity();
        cocktailEntity.setId(cocktailID);
        cocktailEntity.setIngredients(new HashSet<>(Arrays.asList("ingr1", "ingr2", "ingr3")));
        shoppingListEntity.setCocktails(Collections.singletonList(cocktailEntity));

        when(shoppingListRepository.findById(shoppingListID)).thenReturn(Optional.of(shoppingListEntity));

        ShoppingList shoppingList = shoppingListService.findShoppingListById(shoppingListID);

        assertEquals(shoppingList.getShoppingListId(), shoppingListID);
        assertEquals(shoppingList.getIngredients(), new HashSet<>(Arrays.asList("ingr1", "ingr2", "ingr3")));
    }

    @Test
    public void testFindAll() {
        UUID shoppingListID = UUID.randomUUID();
        ShoppingListEntity shoppingListEntity = new ShoppingListEntity();
        shoppingListEntity.setId(shoppingListID);

        UUID cocktailID = UUID.randomUUID();
        CocktailEntity cocktailEntity = new CocktailEntity();
        cocktailEntity.setId(cocktailID);
        cocktailEntity.setIngredients(new HashSet<>(Arrays.asList("ingr1", "ingr2", "ingr3")));
        shoppingListEntity.setCocktails(Collections.singletonList(cocktailEntity));

        when(shoppingListRepository.findAll()).thenReturn(Collections.singletonList(shoppingListEntity));

        List<ShoppingList> shoppingList = shoppingListService.findAll();

        assertEquals(shoppingList.get(0).getShoppingListId(), shoppingListID);
        assertEquals(shoppingList.get(0).getIngredients(), new HashSet<>(Arrays.asList("ingr1", "ingr2", "ingr3")));
    }

}
