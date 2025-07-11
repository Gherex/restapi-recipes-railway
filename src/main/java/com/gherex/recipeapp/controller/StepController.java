package com.gherex.recipeapp.controller;

import com.gherex.recipeapp.dto.StepResponseDTO;
import com.gherex.recipeapp.service.StepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/steps")
public class StepController {

    private final StepService stepService;

    @GetMapping
    public ResponseEntity<List<StepResponseDTO>> getAllSteps() {
        List<StepResponseDTO> steps = stepService.getAllSteps();
        return ResponseEntity.ok(steps); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStepById(@PathVariable Long id) {
        try {
            StepResponseDTO step = stepService.getStepById(id);
            return ResponseEntity.ok(step);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStep(@PathVariable Long id) {
        try {
            stepService.deleteStep(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404
        }
    }

}
