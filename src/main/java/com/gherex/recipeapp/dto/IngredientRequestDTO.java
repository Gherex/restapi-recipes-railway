package com.gherex.recipeapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientRequestDTO {

    @NotBlank
    @Size(max = 50)
    private String name;

}
