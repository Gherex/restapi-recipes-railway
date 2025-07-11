package com.gherex.recipeapp.service;

import com.gherex.recipeapp.dto.CategoryRequestDTO;
import com.gherex.recipeapp.dto.CategoryResponseDTO;
import com.gherex.recipeapp.dto.CategoryWithIdResponseDTO;
import com.gherex.recipeapp.entity.Category;
import com.gherex.recipeapp.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Set<CategoryWithIdResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::mapToResponseWithIdDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public CategoryWithIdResponseDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la categoría con ID: " + id));
        return mapToResponseWithIdDTO(category);
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("No se encontró la categoría con ID: " + id);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return mapToResponseDTO(categoryRepository.save(category));
    }

    private CategoryResponseDTO mapToResponseDTO(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setName(category.getName());
        return dto;
    }

    private CategoryWithIdResponseDTO mapToResponseWithIdDTO(Category category) {
        CategoryWithIdResponseDTO dto = new CategoryWithIdResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}
