package com.workout.fitQuest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "exercise_set")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseSet extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @NotNull(message = "Set number is required")
    @Min(value = 1, message = "Set number must be at least 1")
    @Column(name = "set_number", nullable = false)
    private Integer setNumber;

    @Min(value = 0, message = "Reps cannot be negative")
    private Integer reps;

    @Min(value = 0, message = "Weight cannot be negative")
    private Double weight;

    @Min(value = 0, message = "Duration cannot be negative")
    private Integer duration; // in minutes

    @Min(value = 0, message = "Calories burned cannot be negative")
    private Integer caloriesBurned;
}