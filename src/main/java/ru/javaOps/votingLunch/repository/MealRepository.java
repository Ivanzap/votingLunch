package ru.javaOps.votingLunch.repository;

import ru.javaOps.votingLunch.model.Meal;

import java.util.List;

public interface MealRepository {

    Meal save(Meal meal, int userId);

    Boolean delete(int id, int userId);

    Meal get(int id);

    List<Meal> getAll();

    List<Meal> getMenu(int restaurantId);
}
