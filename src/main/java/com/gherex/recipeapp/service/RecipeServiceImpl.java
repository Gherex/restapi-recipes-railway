package com.gherex.recipeapp.service;

import com.gherex.recipeapp.dto.*;
import com.gherex.recipeapp.entity.*;
import com.gherex.recipeapp.repository.CategoryRepository;
import com.gherex.recipeapp.repository.IngredientRepository;
import com.gherex.recipeapp.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<RecipeResponseDTO> getAllRecipes() {
        return recipeRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Recipe> findRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public RecipeResponseDTO getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la receta con ID: " + id));
        return mapToResponseDTO(recipe);
    }

    @Override
    public RecipeResponseDTO createRecipe(RecipeRequestDTO dto) {
        Recipe recipe = new Recipe();

        recipe.setTitle(dto.getTitle());
        recipe.setDescription(dto.getDescription());
        recipe.setDifficulty(dto.getDifficulty());
        recipe.setImageUrl(dto.getImageUrl());
        recipe.setVegan(dto.isVegan());
        recipe.setPortions(dto.getPortions());

        // Steps
        dto.getSteps()
                .forEach(stepDTO -> {
                    Step step = new Step();
                    step.setDescription(stepDTO.getDescription());
                    step.setStepOrder(stepDTO.getStepOrder());
                    step.setRecipe(recipe); // rel inversa
                    recipe.addStep(step);
                });

        // Categories
        List<Category> categories = categoryRepository.findAllById(dto.getCategoryIds());
        for (Category category : categories) {
            RecipeCategory rc = new RecipeCategory();
            rc.setCategory(category);
            recipe.addCategory(rc);
        }

        // Ingredients con cantidades y unidades (relación intermedia)
        dto.getIngredients().forEach(ingDTO -> {

            Ingredient ingredient;

            if (ingDTO.getIngredientId() != null) {
                ingredient = ingredientRepository.findById(ingDTO.getIngredientId())
                        .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado: " + ingDTO.getIngredientId()));
            } else if (ingDTO.getName() != null && !ingDTO.getName().isBlank()) {
                String name = ingDTO.getName();
                ingredient = ingredientRepository.findByNameIgnoreCase(name)
                        .orElseGet(() -> {
                            Ingredient newIngredient = new Ingredient();
                            newIngredient.setName(name); // el setter limpia
                            return ingredientRepository.save(newIngredient);
                        });
            } else {
                throw new RuntimeException("Se debe proporcionar un ID o nombre de ingrediente");
            }

            RecipeIngredient ri = new RecipeIngredient();
            ri.setIngredient(ingredient);
            ri.setRecipe(recipe);
            ri.setQuantity(ingDTO.getQuantity());
            ri.setUnit(ingDTO.getUnit());

            recipe.addIngredient(ri);
        });

        return mapToResponseDTO(recipeRepository.save(recipe));
    }

    @Override
    public RecipeResponseDTO updateRecipe(Long id, RecipeRequestDTO dto) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la receta con ID: " + id));

        recipe.setTitle(dto.getTitle());
        recipe.setDescription(dto.getDescription());
        recipe.setDifficulty(dto.getDifficulty());
        recipe.setImageUrl(dto.getImageUrl());
        recipe.setVegan(dto.isVegan());
        recipe.setPortions(dto.getPortions());

        // agrego pasos modificados (si aplica)
        if (dto.getSteps() != null) {
            recipe.getSteps().clear();
            dto.getSteps().forEach(stepDTO -> {
                Step step = new Step();
                step.setStepOrder(stepDTO.getStepOrder());
                step.setDescription(stepDTO.getDescription());
                step.setRecipe(recipe);
                recipe.addStep(step);
            });
        }

        // agrego ingredientes (si aplica)
        if (dto.getIngredients() != null) {
            recipe.getIngredients().clear();
            dto.getIngredients().forEach(ingredientDTO -> {
                RecipeIngredient recipeIngredient = new RecipeIngredient();
                Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientDTO.getIngredientId());
                ingredient.ifPresent(recipeIngredient::setIngredient);
                recipeIngredient.setUnit(ingredientDTO.getUnit());
                recipeIngredient.setQuantity(ingredientDTO.getQuantity());
                recipeIngredient.setRecipe(recipe);
                recipe.addIngredient(recipeIngredient);
            });
        }

        // agrego categorías (si aplica)
        if (dto.getCategoryIds() != null) {
            recipe.getCategories().clear();
            dto.getCategoryIds().forEach(categoryId -> {
                Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + categoryId));
                RecipeCategory recipeCategory = new RecipeCategory();
                recipeCategory.setCategory(category);
                recipeCategory.setRecipe(recipe);
                recipe.addCategory(recipeCategory);
            });
        }

        return mapToResponseDTO(recipeRepository.save(recipe));
    }

    @Override
    public void deleteRecipe(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new RuntimeException("No se encontró la receta con ID: " + id);
        }
        recipeRepository.deleteById(id);
    }

    private RecipeResponseDTO mapToResponseDTO(Recipe recipe) {

        RecipeResponseDTO dto = new RecipeResponseDTO();
        dto.setId(recipe.getId());
        dto.setTitle(recipe.getTitle());
        dto.setDescription(recipe.getDescription());
        dto.setImageUrl(recipe.getImageUrl());
        dto.setVegan(recipe.isVegan());
        dto.setDifficulty(recipe.getDifficulty());
        dto.setPortions(recipe.getPortions());

        // Map steps
        List<StepResponseDTO> steps = recipe.getSteps().stream().map(step -> {
            StepResponseDTO s = new StepResponseDTO();
            s.setStepOrder(step.getStepOrder());
            s.setDescription(step.getDescription());
            return s;
        }).toList();
        dto.setSteps(steps);

        // Map ingredients
        List<RecipeIngredientResponseDTO> ingredients = recipe.getIngredients().stream().map(ri -> {
            RecipeIngredientResponseDTO i = new RecipeIngredientResponseDTO();
            i.setId(ri.getIngredient().getId());
            i.setIngredientName(ri.getIngredient().getName());
            i.setQuantity(ri.getQuantity());
            i.setUnit(ri.getUnit());
            return i;
        }).toList();
        dto.setIngredients(ingredients);

        // Map categories
        List<RecipeCategoryResponseDTO> categories = recipe.getCategories().stream().map(cat -> {
            RecipeCategoryResponseDTO c = new RecipeCategoryResponseDTO();
            c.setId(cat.getId());
            c.setName(cat.getCategory().getName());
            return c;
        }).toList();
        dto.setCategories(categories);

        return dto;
    }
}
