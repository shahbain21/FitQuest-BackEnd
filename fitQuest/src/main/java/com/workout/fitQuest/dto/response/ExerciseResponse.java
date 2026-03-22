package com.workout.fitQuest.dto.response;

import com.workout.fitQuest.entity.Exercise;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ExerciseResponse {
    private Long id;
    private String exerciseName;
    private String categoryName;
    private LocalDate date;
    private String notes;
    private List<ExerciseSetResponse> sets;
    private LocalDateTime createdAt;

    public static ExerciseResponse from(Exercise exercise) {
        return ExerciseResponse.builder()
                .id(exercise.getId())
                .exerciseName(exercise.getExerciseName().getName())
                .categoryName(exercise.getExerciseName().getCategory().getCategoryName())
                .date(exercise.getDate())
                .notes(exercise.getNotes())
                .sets(exercise.getSets().stream()
                        .map(ExerciseSetResponse::from)
                        .toList())
                .createdAt(exercise.getCreatedAt())
                .build();
    }
}