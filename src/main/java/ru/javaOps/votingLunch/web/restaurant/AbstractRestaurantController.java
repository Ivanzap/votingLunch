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

    public List<Restaurant> getAll() {
        log.info("getAll");
        return service.getAll();
    }
}
