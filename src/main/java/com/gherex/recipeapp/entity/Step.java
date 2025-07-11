package com.gherex.recipeapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "step")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @Setter
    @Column(name = "step_order")
    private Integer stepOrder;

    @Setter
    @Column(length = 300)
    private String description;

}
