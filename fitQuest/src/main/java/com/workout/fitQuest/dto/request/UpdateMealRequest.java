package com.workout.fitQuest.dto.request;

import com.workout.fitQuest.entity.MealType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateMealRequest {

    @NotBlank(message = "Meal name is required")
    private String mealName;

    @NotNull(message = "Meal type is required")
    private MealType mealType;

    @NotNull(message = "Meal date is required")
    private LocalDate mealDate;

    @NotNull(message = "Calories is required")
    @Min(value = 0, message = "Calories cannot be negative")
    private Integer calories;

    @Min(value = 0, message = "Protein cannot be negative")
    private Double protein;

    @Min(value = 0, message = "Carbs cannot be negative")
    private Double carbs;

    @Min(value = 0, message = "Fats cannot be negative")
    private Double fats;
}