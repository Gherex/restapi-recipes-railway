package com.gherex.recipeapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StepRequestDTO {

    @NotNull
    @Min(1)
    private Integer stepOrder;

    @NotBlank
    @Size(max = 300)
    private String description;
}

