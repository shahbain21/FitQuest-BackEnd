package com.workout.fitQuest.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddExerciseSetRequest {

    @NotNull(message = "Set number is required")
    @Min(value = 1, message = "Set number must be at least 1")
    private Integer setNumber;

    @Min(value = 0, message = "Reps cannot be negative")
    private Integer reps;

    @Min(value = 0, message = "Weight cannot be negative")
    private Double weight;

    @Min(value = 0, message = "Duration cannot be negative")
    private Integer duration;

    @Min(value = 0, message = "Calories burned cannot be negative")
    private Integer caloriesBurned;
}