package com.gherex.recipeapp.controller;

import com.gherex.recipeapp.dto.RecipeRequestDTO;
import com.gherex.recipeapp.dto.RecipeResponseDTO;
import com.gherex.recipeapp.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping
    public ResponseEntity<List<RecipeResponseDTO>> getAllRecipes() {
        List<RecipeResponseDTO> recipes = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipes); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipeById(@PathVariable Long id) {
        try {
            RecipeResponseDTO recipe = recipeService.getRecipeById(id);
            return ResponseEntity.ok(recipe); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404
        }
    }

    @PostMapping
    public ResponseEntity<?> postRecipe(@RequestBody @Valid RecipeRequestDTO recipeDTO) {
        try {
            RecipeResponseDTO created = recipeService.createRecipe(recipeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created); // 201 Created
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // 400 Bad Request
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putRecipe(@PathVariable Long id, @RequestBody @Valid RecipeRequestDTO recipeDTO) {
        try {
            RecipeResponseDTO updated = recipeService.updateRecipe(id, recipeDTO);
            return ResponseEntity.ok(updated); // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Long id) {
        try {
            recipeService.deleteRecipe(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404
        }
    }
}

