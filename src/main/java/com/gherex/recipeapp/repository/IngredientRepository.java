package com.gherex.recipeapp.repository;

import com.gherex.recipeapp.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Optional<Ingredient> findByNameIgnoreCase(String ingredientName);

}
