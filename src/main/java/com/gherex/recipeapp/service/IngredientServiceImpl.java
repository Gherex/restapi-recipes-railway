package com.gherex.recipeapp.service;

import com.gherex.recipeapp.dto.IngredientResponseDTO;
import com.gherex.recipeapp.dto.IngredientWithIdResponseDTO;
import com.gherex.recipeapp.entity.Ingredient;
import com.gherex.recipeapp.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public Set<IngredientWithIdResponseDTO> getAllIngredients() {
        return ingredientRepository.findAll().stream()
                .map(this::mapToResponseWithIdDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public IngredientWithIdResponseDTO getIngredientById(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el ingrediente con ID: " + id));
        return mapToResponseWithIdDTO(ingredient);
    }

    @Override
    public void deleteIngredient(Long id) {
        if (!ingredientRepository.existsById(id)) {
            throw new RuntimeException("No se encontró el ingrediente con ID: " + id);
        }
        ingredientRepository.deleteById(id);
    }

    public IngredientResponseDTO mapToResponseDTO(Ingredient ingredient) {
        IngredientResponseDTO dto = new IngredientResponseDTO();
        dto.setName(ingredient.getName());
        return dto;
    }

    public IngredientWithIdResponseDTO mapToResponseWithIdDTO(Ingredient ingredient) {
        IngredientWithIdResponseDTO dto = new IngredientWithIdResponseDTO();
        dto.setId(ingredient.getId());
        dto.setName(ingredient.getName());
        return dto;
    }
}
