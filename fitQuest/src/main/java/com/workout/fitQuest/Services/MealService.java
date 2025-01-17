package com.workout.fitQuest.Services;
import com.workout.fitQuest.Entities.Meal;
import com.workout.fitQuest.Repositorities.MealRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class MealService {
    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<Meal> getAllMealsForUser(Long userId) {
        return mealRepository.findByUserId(userId);
    }

    public List<Meal> getMealsByType(Meal.MealType mealType) {
        return mealRepository.findByMealType(mealType);
    }

    public List<Meal> getMealsWithinCalorieRange(int minCalories, int maxCalories) {
        return mealRepository.findMealsWithinCalorieRange(minCalories, maxCalories);
    }

    public List<Meal> getMealsByUserAndDate(Long userId, LocalDate mealDate) {
        return mealRepository.findMealsByUserAndDate(userId, mealDate);
    }

    public Meal addMeal(Meal meal) {
        meal.setCreatedAt(LocalDateTime.now());
        return mealRepository.save(meal);
    }

    public void deleteMeal(Long mealId) {
        mealRepository.deleteById(mealId);
    }
}
