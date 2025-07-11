package com.gherex.recipeapp.dto;

import com.gherex.recipeapp.enums.Unit;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RecipeIngredientRequestDTO {

    private Long ingredientId; // opcional, ingredientId o name

    private String name;

    @NotNull
    @DecimalMin(value = "0.01", message = "La cantidad debe ser mayor que cero")
    private BigDecimal quantity;

    @NotNull
    private Unit unit;

    // Al menos uno debe estar presente
    @AssertTrue(message = "Debe proporcionar un ID o un nombre de ingrediente")
    public boolean isValidReference() {
        return (ingredientId != null) || (name != null && !name.isBlank());
    }
}
