package ru.ivanzap.votinglunch.service;

import org.springframework.stereotype.Service;
import ru.ivanzap.votinglunch.model.Dish;
import ru.ivanzap.votinglunch.repository.datajpa.DataJpaDishRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.ivanzap.votinglunch.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    public static final LocalDate TODAY = LocalDate.now();

    private final DataJpaDishRepository repository;

    public DishService(DataJpaDishRepository repository) {
        this.repository = repository;
    }

    public Dish create(Dish dish, int restaurantId) {
        return repository.save(dish, restaurantId);
    }

    public void update(Dish dish, int restaurantId) {
        checkNotFoundWithId(repository.save(dish, restaurantId), dish.getId());
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    public Dish get(int id, int restaurantId) {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    public List<Dish> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    public List<Dish> getFilter(int restaurantId, LocalDate toDay) {
        return repository.getFilter(restaurantId, toDay);
    }

    public List<Dish> getToday(int restaurantId) {
        return repository.getToday(restaurantId, TODAY);
    }
}
