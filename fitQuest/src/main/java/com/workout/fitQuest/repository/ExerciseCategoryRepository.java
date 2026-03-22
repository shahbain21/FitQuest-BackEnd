package com.workout.fitQuest.repository;

import com.workout.fitQuest.entity.ExerciseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExerciseCategoryRepository extends JpaRepository<ExerciseCategory, Long> {

    Optional<ExerciseCategory> findByCategoryName(String categoryName);

    boolean existsByCategoryName(String categoryName);
}