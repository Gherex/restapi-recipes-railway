package com.gherex.recipeapp.service;

import com.gherex.recipeapp.dto.RecipeRequestDTO;
import com.gherex.recipeapp.dto.RecipeResponseDTO;
import com.gherex.recipeapp.entity.Recipe;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    RecipeResponseDTO createRecipe(@Valid RecipeRequestDTO recipeDTO);

    Optional<Recipe> findRecipeById(Long id);

    RecipeResponseDTO getRecipeById(Long id);

    List<RecipeResponseDTO> getAllRecipes();

    RecipeResponseDTO updateRecipe(Long id, @Valid RecipeRequestDTO recipeDTO);

    void deleteRecipe(Long id);
}
