package com.ezgroceries.shoppinglist.entities;

import com.ezgroceries.shoppinglist.entities.utils.StringSetConverter;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author jd70695 - Wouter Stinckens
 * @since UOC201910
 */
@Entity
@Table(name = "shoppingList")
public class ShoppingListEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @OneToMany
    @JoinTable(name = "CocktailShoppingList",
        joinColumns = @JoinColumn(name = "cocktailId"),
        inverseJoinColumns = @JoinColumn(name = "shoppingListId"))
    private List<CocktailEntity> cocktails;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CocktailEntity> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<CocktailEntity> cocktails) {
        this.cocktails = cocktails;
    }
}
