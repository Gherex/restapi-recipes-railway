package com.gherex.recipeapp.controller;

import com.gherex.recipeapp.dto.CategoryRequestDTO;
import com.gherex.recipeapp.dto.CategoryResponseDTO;
import com.gherex.recipeapp.dto.RecipeRequestDTO;
import com.gherex.recipeapp.dto.RecipeResponseDTO;
import com.gherex.recipeapp.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Set<CategoryResponseDTO>> getAllCategories() {
        Set<CategoryResponseDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        try {
            CategoryResponseDTO category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(category);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404
        }
    }

    @PostMapping
    public ResponseEntity<?> postCategory(@RequestBody @Valid CategoryRequestDTO categoryDTO) {
        try {
            CategoryResponseDTO created = categoryService.createRecipe(categoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created); // 201 Created
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // 400 Bad Request
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404
        }
    }

}
