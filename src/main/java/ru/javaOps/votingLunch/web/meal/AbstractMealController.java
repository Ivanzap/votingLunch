package ru.javaOps.votingLunch.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javaOps.votingLunch.model.Meal;
import ru.javaOps.votingLunch.service.MealService;

import java.util.List;

import static ru.javaOps.votingLunch.util.ValidationUtil.assureIdConsistent;
import static ru.javaOps.votingLunch.util.ValidationUtil.checkNew;

@Controller
public abstract class AbstractMealController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal);
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(meal);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public List<Meal> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public List<Meal> getMenu(int restaurantId) {
        log.info("getMenu {}", restaurantId);
        return service.getMenu(restaurantId);
    }
}
