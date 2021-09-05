package ru.javaOps.votingLunch.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javaOps.votingLunch.model.Restaurant;
import ru.javaOps.votingLunch.service.RestaurantService;
import ru.javaOps.votingLunch.util.SecurityUtil;

import java.util.List;

import static ru.javaOps.votingLunch.util.ValidationUtil.assureIdConsistent;
import static ru.javaOps.votingLunch.util.ValidationUtil.checkNew;

@Controller
public abstract class AbstractRestaurantController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    public Restaurant create(Restaurant restaurant) {
        int userId = SecurityUtil.authUserId();
        log.info("create {} by user {}", restaurant, userId);
        checkNew(restaurant);
        return service.create(restaurant, userId);
    }

    public void update(Restaurant restaurant, int id) {
        int userId = SecurityUtil.authUserId();
        log.info("update {} with id={}", restaurant, id);
        assureIdConsistent(restaurant, id);
        service.update(restaurant, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete {} by user {}", id, userId);
        service.delete(id, userId);
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
