package com.workout.fitQuest.service;

import com.workout.fitQuest.entity.Exercise;
import com.workout.fitQuest.entity.ExerciseName;
import com.workout.fitQuest.entity.ExerciseSet;
import com.workout.fitQuest.entity.User;
import com.workout.fitQuest.exception.ResourceNotFoundException;
import com.workout.fitQuest.repository.ExerciseRepository;
import com.workout.fitQuest.repository.ExerciseSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseSetRepository exerciseSetRepository;
    private final UserService userService;
    private final ExerciseNameService exerciseNameService;

    public List<Exercise> getExercisesByUser(Long userId) {
        return exerciseRepository.findByUserId(userId);
    }

    public List<Exercise> getExercisesByUserAndDate(Long userId, LocalDate date) {
        return exerciseRepository.findByUserIdAndDate(userId, date);
    }

    public List<Exercise> getExercisesByUserAndDateRange(Long userId, LocalDate startDate, LocalDate endDate) {
        return exerciseRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
    }

    public Exercise getExerciseById(Long exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise", "id", exerciseId));
    }

    public List<ExerciseSet> getExerciseSets(Long exerciseId) {
        return exerciseSetRepository.findByExerciseIdOrderBySetNumberAsc(exerciseId);
    }

    @Transactional
    public Exercise createExercise(Long userId, Long exerciseNameId,
                                   LocalDate date, String notes) {

        User user = userService.getUserById(userId);
        ExerciseName exerciseName = exerciseNameService.getExerciseNameById(exerciseNameId);

        Exercise exercise = Exercise.builder()
                .user(user)
                .exerciseName(exerciseName)
                .date(date)
                .notes(notes)
                .build();

        return exerciseRepository.save(exercise);
    }

    @Transactional
    public ExerciseSet addSetToExercise(Long exerciseId, Integer setNumber,
                                        Integer reps, Double weight,
                                        Integer duration, Integer caloriesBurned) {

        Exercise exercise = getExerciseById(exerciseId);

        ExerciseSet set = ExerciseSet.builder()
                .exercise(exercise)
                .setNumber(setNumber)
                .reps(reps)
                .weight(weight)
                .duration(duration)
                .caloriesBurned(caloriesBurned)
                .build();

        return exerciseSetRepository.save(set);
    }

    @Transactional
    public Exercise updateExercise(Long exerciseId, Long exerciseNameId,
                                   LocalDate date, String notes) {

        Exercise exercise = getExerciseById(exerciseId);
        ExerciseName exerciseName = exerciseNameService.getExerciseNameById(exerciseNameId);

        exercise.setExerciseName(exerciseName);
        exercise.setDate(date);
        exercise.setNotes(notes);

        return exerciseRepository.save(exercise);
    }

    @Transactional
    public void deleteExercise(Long exerciseId) {
        if (!exerciseRepository.existsById(exerciseId)) {
            throw new ResourceNotFoundException("Exercise", "id", exerciseId);
        }
        exerciseRepository.deleteById(exerciseId);
    }

    @Transactional
    public void deleteSet(Long setId) {
        if (!exerciseSetRepository.existsById(setId)) {
            throw new ResourceNotFoundException("ExerciseSet", "id", setId);
        }
        exerciseSetRepository.deleteById(setId);
    }
}