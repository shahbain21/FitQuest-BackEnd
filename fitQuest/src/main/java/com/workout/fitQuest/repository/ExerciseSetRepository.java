package com.workout.fitQuest.repository;

import com.workout.fitQuest.entity.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Long> {

    // All sets for a specific exercise
    List<ExerciseSet> findByExerciseIdOrderBySetNumberAsc(Long exerciseId);
}