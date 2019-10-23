package com.ezgroceries.shoppinglist.entities;

import com.ezgroceries.shoppinglist.entities.utils.StringSetConverter;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author jd70695 - Wouter Stinckens
 * @since UOC201910
 */
@Entity
@Table(name = "cocktail")
public class CocktailEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private String idDrink;
    private String name;
    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
