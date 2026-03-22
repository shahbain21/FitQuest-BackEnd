package com.workout.fitQuest.dto.response;

import com.workout.fitQuest.entity.ExerciseCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExerciseCategoryResponse {
    private Long id;
    private String categoryName;

    public static ExerciseCategoryResponse from(ExerciseCategory category) {
        return ExerciseCategoryResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }
}