package com.gherex.recipeapp.service;

import com.gherex.recipeapp.dto.IngredientWithIdResponseDTO;

import java.util.Set;

public interface IngredientService {
    Set<IngredientWithIdResponseDTO> getAllIngredients();

    IngredientWithIdResponseDTO getIngredientById(Long id);

    void deleteIngredient(Long id);
}
