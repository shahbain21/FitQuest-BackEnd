package com.workout.fitQuest.Controllers;

import com.workout.fitQuest.Entities.Meal;
import com.workout.fitQuest.Services.MealService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
@CrossOrigin(origins = "http://localhost:4200")
public class MealController {
    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping
    public List<Meal> getAllMeals() {
        return mealService.getAllMeals();
    }

    @PutMapping("/{mealId}")
    public Meal updateMeal(@PathVariable Long mealId, @RequestBody Meal meal) {
        return mealService.updateMeal(mealId, meal);
    }
    @PostMapping
    public Meal addMeal(@RequestBody Meal meal) {
        return mealService.addMeal(meal);
    }

    @DeleteMapping("/{mealId}")
    public void deleteMeal(@PathVariable Long mealId) {
        mealService.deleteMeal(mealId);
    }
}

//    @GetMapping("/user/{userId}")
//    public List<Meal> getAllMealsForUser(@PathVariable Long userId) {
//        return mealService.getAllMealsForUser(userId);
//    }

//    @GetMapping("/type/{mealType}")
//    public List<Meal> getMealsByType(@PathVariable Meal.MealType mealType) {
//        return mealService.getMealsByType(mealType);
//    }
//
//    @GetMapping("/calories")
//    public List<Meal> getMealsWithinCalorieRange(@RequestParam int minCalories, @RequestParam int maxCalories) {
//        return mealService.getMealsWithinCalorieRange(minCalories, maxCalories);
//    }
//
//    @GetMapping("/user/{userId}/date/{mealDate}")
//    public List<Meal> getMealsByUserAndDate(@PathVariable Long userId, @PathVariable LocalDate mealDate) {
//        return mealService.getMealsByUserAndDate(userId, mealDate);
//    }
