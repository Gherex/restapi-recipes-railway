package com.gherex.recipeapp.repository;

import com.gherex.recipeapp.entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepRepository extends JpaRepository<Step, Long> {
}
