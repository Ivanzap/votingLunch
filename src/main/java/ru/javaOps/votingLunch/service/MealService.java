package ru.javaOps.votingLunch.service;

import org.springframework.stereotype.Service;
import ru.javaOps.votingLunch.model.Meal;
import ru.javaOps.votingLunch.repository.MealRepository;

import java.util.List;

import static ru.javaOps.votingLunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    public void update(Meal meal) {
        checkNotFoundWithId(repository.save(meal), meal.getId());
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Meal get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Meal> getAll() {
        return repository.getAll();
    }

    public List<Meal> getMenu(int restaurantId) {
        return repository.getMenu(restaurantId);
    }
}
