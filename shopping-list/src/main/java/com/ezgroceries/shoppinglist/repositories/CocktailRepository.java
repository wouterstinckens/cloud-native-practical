package com.ezgroceries.shoppinglist.repositories;

import com.ezgroceries.shoppinglist.entities.CocktailEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jd70695 - Wouter Stinckens
 * @since UOC201910
 */
public interface CocktailRepository extends CrudRepository<CocktailEntity, UUID> {

    List<CocktailEntity> findByIdDrinkIn(List<String> ids);

}
