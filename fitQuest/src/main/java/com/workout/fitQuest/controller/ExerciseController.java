package com.workout.fitQuest.controller;

import com.workout.fitQuest.dto.request.AddExerciseSetRequest;
import com.workout.fitQuest.dto.request.CreateExerciseRequest;
import com.workout.fitQuest.dto.response.ExerciseResponse;
import com.workout.fitQuest.dto.response.ExerciseSetResponse;
import com.workout.fitQuest.entity.Exercise;
import com.workout.fitQuest.entity.ExerciseSet;
import com.workout.fitQuest.service.ExerciseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping
    public List<ExerciseResponse> getExercises(@PathVariable Long userId,
                                               @RequestParam(required = false) LocalDate date,
                                               @RequestParam(required = false) LocalDate startDate,
                                               @RequestParam(required = false) LocalDate endDate) {
        List<Exercise> exercises;

        if (date != null) {
            exercises = exerciseService.getExercisesByUserAndDate(userId, date);
        } else if (startDate != null && endDate != null) {
            exercises = exerciseService.getExercisesByUserAndDateRange(userId, startDate, endDate);
        } else {
            exercises = exerciseService.getExercisesByUser(userId);
        }

        return exercises.stream().map(ExerciseResponse::from).toList();
    }

    @GetMapping("/{exerciseId}")
    public ExerciseResponse getExercise(@PathVariable Long userId,
                                        @PathVariable Long exerciseId) {
        return ExerciseResponse.from(exerciseService.getExerciseById(exerciseId));
    }

    @PostMapping
    public ResponseEntity<ExerciseResponse> createExercise(@PathVariable Long userId,
                                                           @Valid @RequestBody CreateExerciseRequest request) {
        Exercise exercise = exerciseService.createExercise(
                userId,
                request.getExerciseNameId(),
                request.getDate(),
                request.getNotes()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(ExerciseResponse.from(exercise));
    }

    @PostMapping("/{exerciseId}/sets")
    public ResponseEntity<ExerciseSetResponse> addSet(@PathVariable Long userId,
                                                      @PathVariable Long exerciseId,
                                                      @Valid @RequestBody AddExerciseSetRequest request) {
        ExerciseSet set = exerciseService.addSetToExercise(
                exerciseId,
                request.getSetNumber(),
                request.getReps(),
                request.getWeight(),
                request.getDuration(),
                request.getCaloriesBurned()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(ExerciseSetResponse.from(set));
    }

    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long userId,
                                               @PathVariable Long exerciseId) {
        exerciseService.deleteExercise(exerciseId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{exerciseId}/sets/{setId}")
    public ResponseEntity<Void> deleteSet(@PathVariable Long userId,
                                          @PathVariable Long exerciseId,
                                          @PathVariable Long setId) {
        exerciseService.deleteSet(setId);
        return ResponseEntity.noContent().build();
    }
}