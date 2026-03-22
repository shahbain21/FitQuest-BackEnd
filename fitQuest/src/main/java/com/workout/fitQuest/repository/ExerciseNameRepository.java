package com.workout.fitQuest.repository;

import com.workout.fitQuest.entity.ExerciseName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseNameRepository extends JpaRepository<ExerciseName, Long> {

    // All exercises in a category
    List<ExerciseName> findByCategoryId(Long categoryId);

    Optional<ExerciseName> findByName(String name);

    boolean existsByName(String name);
}
