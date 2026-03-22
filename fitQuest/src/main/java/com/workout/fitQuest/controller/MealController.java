package com.workout.fitQuest.controller;

import com.workout.fitQuest.dto.request.CreateMealRequest;
import com.workout.fitQuest.dto.request.UpdateMealRequest;
import com.workout.fitQuest.dto.response.MealResponse;
import com.workout.fitQuest.entity.Meal;
import com.workout.fitQuest.service.MealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping
    public List<MealResponse> getMeals(@PathVariable Long userId,
                                       @RequestParam(required = false) LocalDate date,
                                       @RequestParam(required = false) LocalDate startDate,
                                       @RequestParam(required = false) LocalDate endDate) {
        List<Meal> meals;

        if (date != null) {
            meals = mealService.getMealsByUserAndDate(userId, date);
        } else if (startDate != null && endDate != null) {
            meals = mealService.getMealsByUserAndDateRange(userId, startDate, endDate);
        } else {
            meals = mealService.getMealsByUser(userId);
        }

        return meals.stream().map(MealResponse::from).toList();
    }

    @GetMapping("/{mealId}")
    public MealResponse getMeal(@PathVariable Long userId,
                                @PathVariable Long mealId) {
        return MealResponse.from(mealService.getMealById(mealId));
    }

    @PostMapping
    public ResponseEntity<MealResponse> createMeal(@PathVariable Long userId,
                                                   @Valid @RequestBody CreateMealRequest request) {
        Meal meal = mealService.createMeal(
                userId,
                request.getMealName(),
                request.getMealType(),
                request.getMealDate(),
                request.getCalories(),
                request.getProtein(),
                request.getCarbs(),
                request.getFats()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(MealResponse.from(meal));
    }

    @PutMapping("/{mealId}")
    public MealResponse updateMeal(@PathVariable Long userId,
                                   @PathVariable Long mealId,
                                   @Valid @RequestBody UpdateMealRequest request) {
        Meal meal = mealService.updateMeal(
                mealId,
                request.getMealName(),
                request.getMealType(),
                request.getMealDate(),
                request.getCalories(),
                request.getProtein(),
                request.getCarbs(),
                request.getFats()
        );
        return MealResponse.from(meal);
    }

    @DeleteMapping("/{mealId}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long userId,
                                           @PathVariable Long mealId) {
        mealService.deleteMeal(mealId);
        return ResponseEntity.noContent().build();
    }
}