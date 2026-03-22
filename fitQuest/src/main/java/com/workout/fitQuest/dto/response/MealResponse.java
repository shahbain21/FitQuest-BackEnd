package com.workout.fitQuest.dto.response;

import com.workout.fitQuest.entity.Meal;
import com.workout.fitQuest.entity.MealType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class MealResponse {
    private Long id;
    private String mealName;
    private MealType mealType;
    private LocalDate mealDate;
    private Integer calories;
    private Double protein;
    private Double carbs;
    private Double fats;
    private LocalDateTime createdAt;

    public static MealResponse from(Meal meal) {
        return MealResponse.builder()
                .id(meal.getId())
                .mealName(meal.getMealName())
                .mealType(meal.getMealType())
                .mealDate(meal.getMealDate())
                .calories(meal.getCalories())
                .protein(meal.getProtein())
                .carbs(meal.getCarbs())
                .fats(meal.getFats())
                .createdAt(meal.getCreatedAt())
                .build();
    }
}
