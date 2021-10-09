package ru.ivanzap.votinglunch.service;

import org.springframework.stereotype.Service;
import ru.ivanzap.votinglunch.model.Restaurant;
import ru.ivanzap.votinglunch.repository.datajpa.DataJpaRestaurantRepository;

import java.util.List;

import static ru.ivanzap.votinglunch.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final DataJpaRestaurantRepository repository;

    public RestaurantService(DataJpaRestaurantRepository repository) {
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

    public Restaurant getWithDishes(int id) {
        return checkNotFoundWithId(repository.getWithDishes(id), id);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }
}
