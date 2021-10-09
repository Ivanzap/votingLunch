package ru.ivanzap.votinglunch.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ivanzap.votinglunch.model.Restaurant;
import ru.ivanzap.votinglunch.service.RestaurantService;

import java.util.List;

import static ru.ivanzap.votinglunch.util.validation.ValidationUtil.assureIdConsistent;
import static ru.ivanzap.votinglunch.util.validation.ValidationUtil.checkNew;

public abstract class AbstractRestaurantController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return service.create(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} with id={}", restaurant, id);
        assureIdConsistent(restaurant, id);
        service.update(restaurant);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public Restaurant get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Restaurant getWithDishes(int id) {
        log.info("getWithDishes {}", id);
        return service.getWithDishes(id);
    }

    public List<Restaurant> getAll() {
        log.info("getAll");
        return service.getAll();
    }
}
