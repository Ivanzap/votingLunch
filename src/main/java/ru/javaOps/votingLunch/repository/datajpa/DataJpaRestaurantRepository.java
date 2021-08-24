package ru.javaOps.votingLunch.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javaOps.votingLunch.model.Restaurant;
import ru.javaOps.votingLunch.repository.RestaurantRepository;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {
    @Override
    public Restaurant save(Restaurant restaurant, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Restaurant get(int id, int userId) {
        return null;
    }

    @Override
    public List<Restaurant> getAll(int userId) {
        return null;
    }
}
