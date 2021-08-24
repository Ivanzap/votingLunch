package ru.javaOps.votingLunch.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javaOps.votingLunch.model.Restaurant;
import ru.javaOps.votingLunch.service.RestaurantService;

import java.util.List;

import static ru.javaOps.votingLunch.util.ValidationUtil.assureIdConsistent;
import static ru.javaOps.votingLunch.util.ValidationUtil.checkNew;

@Controller
public abstract class AbstractRestaurantController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    public Restaurant create(Restaurant restaurant, int userId) {
        log.info("create {} with id={}", restaurant, userId);
        checkNew(restaurant);
        return service.create(restaurant, userId);
    }

    public void update(Restaurant restaurant, int userId) {
        log.info("update {} with id={}", restaurant, userId);
        assureIdConsistent(restaurant, userId);
        service.update(restaurant, userId);
    }

    public void delete(int id, int userId) {
        log.info("delete {}", id);
        service.delete(id, userId);
    }

    public Restaurant get(int id, int userId) {
        log.info("get {}", id);
        return service.get(id, userId);
    }

    public List<Restaurant> getAll(int userId) {
        log.info("getAll");
        return service.getAll(userId);
    }
}
