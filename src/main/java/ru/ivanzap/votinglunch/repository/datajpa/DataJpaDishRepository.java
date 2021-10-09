package ru.ivanzap.votinglunch.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanzap.votinglunch.model.Dish;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaDishRepository {

    private final CrudDishRepository crudDishRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaDishRepository(CrudDishRepository crudDishRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudDishRepository = crudDishRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        dish.setRestaurant(crudRestaurantRepository.getById(restaurantId));
        return crudDishRepository.save(dish);
    }

    public boolean delete(int id, int restaurantId) {
        return crudDishRepository.delete(id, restaurantId) != 0;
    }

    public Dish get(int id, int restaurantId) {
        return crudDishRepository.findById(id)
                .filter(m -> m.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }

    public List<Dish> getAll(int restaurantId) {
        return crudDishRepository.getAll(restaurantId);
    }

    public List<Dish> getFilter(int restaurantId, LocalDate toDay) {
        return crudDishRepository.getFilter(restaurantId, toDay);
    }

    public List<Dish> getToday(int restaurantId, LocalDate toDay) {
        return crudDishRepository.getToday(restaurantId, toDay);
    }
}
