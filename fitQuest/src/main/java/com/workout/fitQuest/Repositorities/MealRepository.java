package com.workout.fitQuest.Repositorities;

import com.workout.fitQuest.Entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    // Find all meals by a user
    List<Meal> findByUserId(Long userId);

    // Find meals by type
    List<Meal> findByMealType(Meal.MealType mealType);

    // Custom query: Find meals within a specific calorie range
    @Query("SELECT m FROM Meal m WHERE m.calories BETWEEN :minCalories AND :maxCalories")
    List<Meal> findMealsWithinCalorieRange(@Param("minCalories") int minCalories, @Param("maxCalories") int maxCalories);

    // Custom query: Find meals on a specific date for a user
    @Query("SELECT m FROM Meal m WHERE m.user.id = :userId AND m.mealDate = :mealDate")
    List<Meal> findMealsByUserAndDate(@Param("userId") Long userId, @Param("mealDate") LocalDate mealDate);
}