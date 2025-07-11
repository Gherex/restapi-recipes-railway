package com.gherex.recipeapp.service;

import com.gherex.recipeapp.dto.StepResponseDTO;

import java.util.List;

public interface StepService {
    List<StepResponseDTO> getAllSteps();

    StepResponseDTO getStepById(Long id);

    void deleteStep(Long id);
}
