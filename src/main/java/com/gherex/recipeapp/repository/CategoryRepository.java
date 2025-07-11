package com.gherex.recipeapp.repository;

import com.gherex.recipeapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
