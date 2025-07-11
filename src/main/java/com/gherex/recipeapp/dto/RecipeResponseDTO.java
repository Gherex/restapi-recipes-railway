package com.gherex.recipeapp.dto;

import com.gherex.recipeapp.enums.Difficulty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Difficulty difficulty;
    private int portions;
    private boolean isVegan;

    private List<StepResponseDTO> steps;
    private List<RecipeIngredientResponseDTO> ingredients;
    private List<RecipeCategoryResponseDTO> categories;

}
