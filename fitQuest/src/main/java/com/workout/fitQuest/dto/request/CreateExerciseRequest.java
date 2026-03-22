package com.workout.fitQuest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateExerciseRequest {

    @NotNull(message = "Exercise name ID is required")
    private Long exerciseNameId;

    @NotNull(message = "Date is required")
    private LocalDate date;

    private String notes;
}