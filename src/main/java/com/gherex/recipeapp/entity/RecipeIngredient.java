package com.gherex.recipeapp.entity;

import com.gherex.recipeapp.enums.Unit;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "recipe_ingredient")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private BigDecimal quantity;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Unit unit;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecipeIngredient that)) return false;
        return recipe.equals(that.recipe) && ingredient.equals(that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipe, ingredient);
    }


}
