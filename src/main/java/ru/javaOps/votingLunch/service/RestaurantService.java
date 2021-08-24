package ru.javaOps.votingLunch.service;

import org.springframework.stereotype.Service;
import ru.javaOps.votingLunch.model.Restaurant;
import ru.javaOps.votingLunch.repository.RestaurantRepository;

import java.util.List;

import static ru.javaOps.votingLunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant create(Restaurant restaurant, int userId) {
        return repository.save(restaurant, userId);
    }

    public void update(Restaurant restaurant, int userId) {
        checkNotFoundWithId(repository.save(restaurant, userId), restaurant.getId());
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public Restaurant get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public List<Restaurant> getAll(int userId) {
        return repository.getAll(userId);
    }
}
