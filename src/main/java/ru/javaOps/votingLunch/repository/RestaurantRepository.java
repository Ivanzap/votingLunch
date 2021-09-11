package ru.javaOps.votingLunch.repository;

import ru.javaOps.votingLunch.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant, int userId);

    boolean delete(int id, int userId);

    Restaurant get(int id);

    List<Restaurant> getAll();
}
