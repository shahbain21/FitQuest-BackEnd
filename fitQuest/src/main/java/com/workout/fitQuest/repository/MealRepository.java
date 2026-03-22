package com.workout.fitQuest.repository;

import com.workout.fitQuest.entity.Meal;
import com.workout.fitQuest.entity.MealType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {

    // All meals for a user
    List<Meal> findByUserId(Long userId);

    // All meals for a user on a specific date
    List<Meal> findByUserIdAndMealDate(Long userId, LocalDate mealDate);

    // All meals for a user within a date range
    List<Meal> findByUserIdAndMealDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    // All meals for a user of a specific type
    List<Meal> findByUserIdAndMealType(Long userId, MealType mealType);
}