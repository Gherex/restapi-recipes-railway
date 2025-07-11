package com.gherex.recipeapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeCategoryRequestDTO {

    @NotNull
    private Long categoryId;

}
