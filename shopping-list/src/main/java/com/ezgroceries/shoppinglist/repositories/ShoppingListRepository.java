package com.ezgroceries.shoppinglist.repositories;

import com.ezgroceries.shoppinglist.entities.ShoppingListEntity;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jd70695 - Wouter Stinckens
 * @since UOC201910
 */
public interface ShoppingListRepository extends CrudRepository<ShoppingListEntity, UUID> {

}
