package com.workout.fitQuest.controller;

import com.workout.fitQuest.dto.request.CreateExerciseNameRequest;
import com.workout.fitQuest.dto.response.ExerciseNameResponse;
import com.workout.fitQuest.entity.ExerciseName;
import com.workout.fitQuest.service.ExerciseNameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercise-names")
@RequiredArgsConstructor
public class ExerciseNameController {

    private final ExerciseNameService exerciseNameService;

    @GetMapping
    public List<ExerciseNameResponse> getAllExerciseNames(
            @RequestParam(required = false) Long categoryId) {
        List<ExerciseName> names;

        if (categoryId != null) {
            names = exerciseNameService.getExerciseNamesByCategory(categoryId);
        } else {
            names = exerciseNameService.getAllExerciseNames();
        }

        return names.stream().map(ExerciseNameResponse::from).toList();
    }

    @PostMapping
    public ResponseEntity<ExerciseNameResponse> createExerciseName(
            @Valid @RequestBody CreateExerciseNameRequest request) {
        ExerciseName name = exerciseNameService.createExerciseName(
                request.getName(),
                request.getCategoryId()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ExerciseNameResponse.from(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExerciseName(@PathVariable Long id) {
        exerciseNameService.deleteExerciseName(id);
        return ResponseEntity.noContent().build();
    }
}