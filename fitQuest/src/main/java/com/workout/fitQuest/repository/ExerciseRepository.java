package com.workout.fitQuest.repository;

import com.workout.fitQuest.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    // All exercises for a user
    List<Exercise> findByUserId(Long userId);

    // All exercises for a user on a specific date
    List<Exercise> findByUserIdAndDate(Long userId, LocalDate date);

    // All exercises for a user within a date range
    List<Exercise> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    // All exercises for a user with a specific exercise name
    List<Exercise> findByUserIdAndExerciseNameId(Long userId, Long exerciseNameId);
}
