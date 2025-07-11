package com.gherex.recipeapp.entity;

import com.gherex.recipeapp.enums.Difficulty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recipe")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(length = 100, nullable = false)
    private String title;

    @Setter
    @Column(length = 500)
    private String description;

    @Setter
    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    @Setter
    private int portions;

    @Setter
    @Column(name = "is_vegan", nullable = false)
    private boolean isVegan;

    @Setter
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("stepOrder ASC")
    private List<Step> steps = new ArrayList<>();

    @Setter
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RecipeIngredient> ingredients = new HashSet<>();

    @Setter
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RecipeCategory> categories = new HashSet<>();

    public void addStep(Step step) {
        steps.add(step);
        step.setRecipe(this);
    }

    public void removeStep(Step step) {
        steps.remove(step);
        step.setRecipe(null);
    }

    public void addIngredient(RecipeIngredient ingredient) {
        if (ingredients.add(ingredient)) ingredient.setRecipe(this);
    }

    public void removeIngredient(RecipeIngredient ingredient) {
        if (ingredients.remove(ingredient)) ingredient.setRecipe(null);
    }

    public void addCategory(RecipeCategory category) {
        if (categories.add(category)) category.setRecipe(this);
    }

    public void removeCategory(RecipeCategory category) {
        if (categories.remove(category)) category.setRecipe(null);
    }

}
