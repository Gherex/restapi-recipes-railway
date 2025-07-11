package com.gherex.recipeapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ingredient")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(unique = true, length = 50)
    private String name;

    public void setName(String name) {
        if (name != null) {
            this.name = name.trim().toLowerCase();
        }
    }

}
