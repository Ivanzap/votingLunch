package ru.javaOps.votingLunch.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javaOps.votingLunch.model.Meal;
import ru.javaOps.votingLunch.model.Role;
import ru.javaOps.votingLunch.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {
    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "date_time", "restaurant_id");

    private final CrudMealRepository crudMealRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaMealRepository(CrudMealRepository crudMealRepository, CrudUserRepository crudUserRepository) {
        this.crudMealRepository = crudMealRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (!meal.isNew() && get(meal.getId()) == null) {
            return null;
        }
        if (!crudUserRepository.findById(userId).orElseThrow().getRoles().contains(Role.ADMIN)) {
            return null;
        }
        meal.setUser(crudUserRepository.getById(userId));
        return crudMealRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        if (!crudUserRepository.findById(userId).orElseThrow().getRoles().contains(Role.ADMIN)) {
            return false;
        }
        return crudMealRepository.delete(id) != 0;
    }

    @Override
    public Meal get(int id) {
        return crudMealRepository.findById(id).orElse(null);
    }

    @Override
    public List<Meal> getAllMenuOfRestaurant() {
        return crudMealRepository.findAll(SORT_NAME);
    }

    @Override
    public List<Meal> getAllMenuOfRestaurant(int restaurantId) {
        return crudMealRepository.getAllMenuOfRestaurant(restaurantId);
    }

    @Override
    public List<Meal> getMenuTodayOfRestaurant(int restaurantId, LocalDateTime toDay) {
        return crudMealRepository.getMenuTodayOfRestaurant(restaurantId, toDay);
    }
}
