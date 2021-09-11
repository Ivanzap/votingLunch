package ru.javaOps.votingLunch.repository;

import ru.javaOps.votingLunch.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {

    Meal save(Meal meal, int userId);

    boolean delete(int id, int userId);

    Meal get(int id);

    List<Meal> getAllMenuOfRestaurant();

    List<Meal> getAllMenuOfRestaurant(int restaurantId);

    List<Meal> getMenuTodayOfRestaurant(int restaurantId, LocalDateTime toDay);
}
