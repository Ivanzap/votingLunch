package ru.javaOps.votingLunch.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javaOps.votingLunch.model.Restaurant;
import ru.javaOps.votingLunch.model.Role;
import ru.javaOps.votingLunch.repository.RestaurantRepository;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {
    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRestaurantRepository, CrudUserRepository crudUserRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant, int userId) {
        if (!restaurant.isNew() && get(restaurant.getId()) == null) {
            return null;
        }
        if (!crudUserRepository.findById(userId).orElseThrow().getRoles().contains(Role.ADMIN)) {
            return null;
        }
        restaurant.setUser(crudUserRepository.getById(userId));
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id, int userId) {
        if (!crudUserRepository.findById(userId).orElseThrow().getRoles().contains(Role.ADMIN)) {
            return false;
        }
        return crudRestaurantRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll(SORT_NAME);
    }
}
