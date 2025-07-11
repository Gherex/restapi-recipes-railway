package com.gherex.recipeapp.service;

import com.gherex.recipeapp.dto.IngredientResponseDTO;

import java.util.Set;

public interface IngredientService {
    Set<IngredientResponseDTO> getAllIngredients();

    IngredientResponseDTO getIngredientById(Long id);

    void deleteIngredient(Long id);
}
