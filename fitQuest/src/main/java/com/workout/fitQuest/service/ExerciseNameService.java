package com.workout.fitQuest.service;

import com.workout.fitQuest.entity.ExerciseCategory;
import com.workout.fitQuest.entity.ExerciseName;
import com.workout.fitQuest.exception.DuplicateResourceException;
import com.workout.fitQuest.exception.ResourceNotFoundException;
import com.workout.fitQuest.repository.ExerciseNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseNameService {

    private final ExerciseNameRepository exerciseNameRepository;
    private final ExerciseCategoryService categoryService;

    public List<ExerciseName> getAllExerciseNames() {
        return exerciseNameRepository.findAll();
    }

    public List<ExerciseName> getExerciseNamesByCategory(Long categoryId) {
        return exerciseNameRepository.findByCategoryId(categoryId);
    }

    public ExerciseName getExerciseNameById(Long id) {
        return exerciseNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ExerciseName", "id", id));
    }

    @Transactional
    public ExerciseName createExerciseName(String name, Long categoryId) {
        if (exerciseNameRepository.existsByName(name)) {
            throw new DuplicateResourceException("ExerciseName", "name", name);
        }

        ExerciseCategory category = categoryService.getCategoryById(categoryId);

        ExerciseName exerciseName = ExerciseName.builder()
                .name(name)
                .category(category)
                .build();

        return exerciseNameRepository.save(exerciseName);
    }

    @Transactional
    public void deleteExerciseName(Long id) {
        if (!exerciseNameRepository.existsById(id)) {
            throw new ResourceNotFoundException("ExerciseName", "id", id);
        }
        exerciseNameRepository.deleteById(id);
    }
}