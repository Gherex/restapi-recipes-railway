package com.gherex.recipeapp.dto;

import com.gherex.recipeapp.enums.Difficulty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeRequestDTO {

    @NotBlank(message = "El título no puede quedar vacío")
    @Size(max = 100)
    private String title;

    @Size(max = 500)
    private String description;

    @Size(max = 500)
    private String imageUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Min(1)
    private int portions;

    private boolean isVegan;

    @NotEmpty
    private List<StepRequestDTO> steps;

    @NotEmpty
    private List<RecipeIngredientRequestDTO> ingredients;

    private List<Long> categoryIds;

}

