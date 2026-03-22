package com.workout.fitQuest.dto.response;

import com.workout.fitQuest.entity.ExerciseSet;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExerciseSetResponse {
    private Long id;
    private Integer setNumber;
    private Integer reps;
    private Double weight;
    private Integer duration;
    private Integer caloriesBurned;

    public static ExerciseSetResponse from(ExerciseSet set) {
        return ExerciseSetResponse.builder()
                .id(set.getId())
                .setNumber(set.getSetNumber())
                .reps(set.getReps())
                .weight(set.getWeight())
                .duration(set.getDuration())
                .caloriesBurned(set.getCaloriesBurned())
                .build();
    }
}
