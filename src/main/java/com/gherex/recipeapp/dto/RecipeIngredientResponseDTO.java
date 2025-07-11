package com.gherex.recipeapp.dto;

import com.gherex.recipeapp.enums.Unit;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RecipeIngredientResponseDTO {

    private Long id;
    private String ingredientName;
    private BigDecimal quantity;
    private Unit unit;

}
