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

    public Restaurant create(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public void update(Restaurant restaurant) {
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }
}
