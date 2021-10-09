package ru.ivanzap.votinglunch.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ivanzap.votinglunch.model.Dish;
import ru.ivanzap.votinglunch.service.DishService;

import java.time.LocalDate;
import java.util.List;

import static ru.ivanzap.votinglunch.util.validation.ValidationUtil.assureIdConsistent;
import static ru.ivanzap.votinglunch.util.validation.ValidationUtil.checkNew;

public abstract class AbstractDishController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    public Dish create(Dish dish, int restaurantId) {
        log.info("create {}", dish);
        checkNew(dish);
        return service.create(dish, restaurantId);
    }

    public void update(Dish dish, int restaurantId) {
        log.info("update {}", dish);
        assureIdConsistent(dish, dish.getId());
        service.update(dish, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        log.info("delete {}", id);
        service.delete(id, restaurantId);
    }

    public Dish get(int id, int restaurantId) {
        log.info("get {}", id);
        return service.get(id, restaurantId);
    }

    public List<Dish> getAll(int restaurantId) {
        log.info("getAll");
        return service.getAll(restaurantId);
    }

    public List<Dish> getFilter(LocalDate date, int restaurantId) {
        log.info("getFilter {}", restaurantId);
        return service.getFilter(restaurantId, date);
    }

    public List<Dish> getToday(int restaurantId) {
        log.info("getToday {}", restaurantId);
        return service.getToday(restaurantId);
    }
}
