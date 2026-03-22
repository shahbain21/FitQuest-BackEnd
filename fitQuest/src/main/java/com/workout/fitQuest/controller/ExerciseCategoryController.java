package com.workout.fitQuest.controller;

import com.workout.fitQuest.dto.request.CreateCategoryRequest;
import com.workout.fitQuest.dto.response.ExerciseCategoryResponse;
import com.workout.fitQuest.entity.ExerciseCategory;
import com.workout.fitQuest.service.ExerciseCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercise-categories")
@RequiredArgsConstructor
public class ExerciseCategoryController {

    private final ExerciseCategoryService categoryService;

    @GetMapping
    public List<ExerciseCategoryResponse> getAllCategories() {
        return categoryService.getAllCategories().stream()
                .map(ExerciseCategoryResponse::from)
                .toList();
    }

    @PostMapping
    public ResponseEntity<ExerciseCategoryResponse> createCategory(
            @Valid @RequestBody CreateCategoryRequest request) {
        ExerciseCategory category = categoryService.createCategory(request.getCategoryName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ExerciseCategoryResponse.from(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
