package com.gherex.recipeapp.service;

import com.gherex.recipeapp.dto.StepResponseDTO;
import com.gherex.recipeapp.entity.Step;
import com.gherex.recipeapp.repository.StepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StepServiceImpl implements StepService {

    private final StepRepository stepRepository;

    @Override
    public List<StepResponseDTO> getAllSteps() {
        return stepRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StepResponseDTO getStepById(Long id) {
        Step step = stepRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el step con ID: " + id));
        return mapToResponseDTO(step);
    }

    @Override
    public void deleteStep(Long id) {
        if (!stepRepository.existsById(id)) {
            throw new RuntimeException("No se encontró el step con ID: " + id);
        }
        stepRepository.deleteById(id);
    }

    private StepResponseDTO mapToResponseDTO(Step step) {
        StepResponseDTO dto = new StepResponseDTO();
        dto.setStepOrder(step.getStepOrder());
        dto.setDescription(step.getDescription());
        return dto;
    }
}