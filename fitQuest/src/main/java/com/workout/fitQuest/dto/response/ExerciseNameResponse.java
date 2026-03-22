package com.workout.fitQuest.dto.response;

import com.workout.fitQuest.entity.ExerciseName;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExerciseNameResponse {
    private Long id;
    private String name;
    private String categoryName;

    public static ExerciseNameResponse from(ExerciseName exerciseName) {
        return ExerciseNameResponse.builder()
                .id(exerciseName.getId())
                .name(exerciseName.getName())
                .categoryName(exerciseName.getCategory().getCategoryName())
                .build();
    }
}