package com.workout.fitQuest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExerciseNameRequest {

    @NotBlank(message = "Exercise name is required")
    private String name;

    @NotNull(message = "Category ID is required")
    private Long categoryId;
}