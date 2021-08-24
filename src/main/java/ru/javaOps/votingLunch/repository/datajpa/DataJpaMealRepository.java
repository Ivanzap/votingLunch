package ru.javaOps.votingLunch.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javaOps.votingLunch.model.Meal;
import ru.javaOps.votingLunch.repository.MealRepository;

import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {
    @Override
    public Meal save(Meal meal, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return null;
    }
}
