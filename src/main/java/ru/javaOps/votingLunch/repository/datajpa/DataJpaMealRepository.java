package ru.javaOps.votingLunch.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javaOps.votingLunch.model.Meal;
import ru.javaOps.votingLunch.repository.MealRepository;

import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {
    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "date_time", "restaurant_id");

    private final CrudMealRepository crudRepository;

    public DataJpaMealRepository(CrudMealRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Meal save(Meal meal) {
        return crudRepository.save(meal);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Meal get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Meal> getAll() {
        return crudRepository.findAll(SORT_NAME);
    }

    @Override
    public List<Meal> getMenu(int restaurantId) {
        return crudRepository.getMenu(restaurantId);
    }
}
