package ru.javaOps.votingLunch.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaOps.votingLunch.model.Meal;
import ru.javaOps.votingLunch.service.MealService;
import ru.javaOps.votingLunch.util.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javaOps.votingLunch.util.ValidationUtil.assureIdConsistent;
import static ru.javaOps.votingLunch.util.ValidationUtil.checkNew;


public abstract class AbstractMealController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal create(Meal meal) {
        int userId = SecurityUtil.authUserId();
        log.info("create {} by user {}", meal, userId);
        checkNew(meal);
        return service.create(meal, userId);
    }

    public void update(Meal meal, int id) {
        int userId = SecurityUtil.authUserId();
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(meal, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete {} by user {}", id, userId);
        service.delete(id, userId);
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public List<Meal> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public List<Meal> getAllMenuOfRestaurant(int restaurantId) {
        log.info("getAllMenuOfRestaurant {}", restaurantId);
        return service.getAllMenuOfRestaurant(restaurantId);
    }

    public List<Meal> getMenuTodayOfRestaurant(int restaurantId) {
        LocalDateTime toDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        log.info("getMenuTodayOfRestaurant {}", restaurantId);
        return service.getMenuTodayOfRestaurant(restaurantId, toDay);
    }
}
