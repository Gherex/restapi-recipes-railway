package com.gherex.recipeapp.repository;

import com.gherex.recipeapp.entity.RecipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory, Long> {
}
