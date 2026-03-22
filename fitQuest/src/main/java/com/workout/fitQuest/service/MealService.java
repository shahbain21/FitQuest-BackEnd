package com.workout.fitQuest.service;

import com.workout.fitQuest.entity.Meal;
import com.workout.fitQuest.entity.MealType;
import com.workout.fitQuest.entity.User;
import com.workout.fitQuest.exception.ResourceNotFoundException;
import com.workout.fitQuest.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final UserService userService;

    public List<Meal> getMealsByUser(Long userId) {
        return mealRepository.findByUserId(userId);
    }

    public List<Meal> getMealsByUserAndDate(Long userId, LocalDate date) {
        return mealRepository.findByUserIdAndMealDate(userId, date);
    }

    public List<Meal> getMealsByUserAndDateRange(Long userId, LocalDate startDate, LocalDate endDate) {
        return mealRepository.findByUserIdAndMealDateBetween(userId, startDate, endDate);
    }

    public List<Meal> getMealsByUserAndType(Long userId, MealType mealType) {
        return mealRepository.findByUserIdAndMealType(userId, mealType);
    }

    public Meal getMealById(Long mealId) {
        return mealRepository.findById(mealId)
                .orElseThrow(() -> new ResourceNotFoundException("Meal", "id", mealId));
    }

    @Transactional
    public Meal createMeal(Long userId, String mealName, MealType mealType,
                           LocalDate mealDate, Integer calories,
                           Double protein, Double carbs, Double fats) {

        User user = userService.getUserById(userId);

        Meal meal = Meal.builder()
                .user(user)
                .mealName(mealName)
                .mealType(mealType)
                .mealDate(mealDate)
                .calories(calories)
                .protein(protein)
                .carbs(carbs)
                .fats(fats)
                .build();

        return mealRepository.save(meal);
    }

    @Transactional
    public Meal updateMeal(Long mealId, String mealName, MealType mealType,
                           LocalDate mealDate, Integer calories,
                           Double protein, Double carbs, Double fats) {

        Meal meal = getMealById(mealId);

        meal.setMealName(mealName);
        meal.setMealType(mealType);
        meal.setMealDate(mealDate);
        meal.setCalories(calories);
        meal.setProtein(protein);
        meal.setCarbs(carbs);
        meal.setFats(fats);

        return mealRepository.save(meal);
    }

    @Transactional
    public void deleteMeal(Long mealId) {
        if (!mealRepository.existsById(mealId)) {
            throw new ResourceNotFoundException("Meal", "id", mealId);
        }
        mealRepository.deleteById(mealId);
    }
}