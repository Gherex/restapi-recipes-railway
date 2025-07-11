package com.gherex.recipeapp.repository;

import com.gherex.recipeapp.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
