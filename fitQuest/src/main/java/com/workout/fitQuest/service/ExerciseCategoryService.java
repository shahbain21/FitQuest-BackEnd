package com.workout.fitQuest.service;

import com.workout.fitQuest.entity.ExerciseCategory;
import com.workout.fitQuest.exception.DuplicateResourceException;
import com.workout.fitQuest.exception.ResourceNotFoundException;
import com.workout.fitQuest.repository.ExerciseCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseCategoryService {

    private final ExerciseCategoryRepository categoryRepository;

    public List<ExerciseCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    public ExerciseCategory getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ExerciseCategory", "id", id));
    }

    @Transactional
    public ExerciseCategory createCategory(String categoryName) {
        if (categoryRepository.existsByCategoryName(categoryName)) {
            throw new DuplicateResourceException("ExerciseCategory", "name", categoryName);
        }

        ExerciseCategory category = ExerciseCategory.builder()
                .categoryName(categoryName)
                .build();

        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("ExerciseCategory", "id", id);
        }
        categoryRepository.deleteById(id);
    }
}