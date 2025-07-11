package com.gherex.recipeapp.controller;

import com.gherex.recipeapp.dto.IngredientResponseDTO;
import com.gherex.recipeapp.dto.IngredientWithIdResponseDTO;
import com.gherex.recipeapp.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<Set<IngredientWithIdResponseDTO>> getAllIngredients() {
        Set<IngredientWithIdResponseDTO> ingredients = ingredientService.getAllIngredients();
        return ResponseEntity.ok(ingredients); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIngredientById(@PathVariable Long id) {
        try {
            IngredientWithIdResponseDTO ingredient = ingredientService.getIngredientById(id);
            return ResponseEntity.ok(ingredient); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable Long id) {
        try {
            ingredientService.deleteIngredient(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404
        }
    }

}
