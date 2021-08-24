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

    public Meal create(Meal meal, int userId) {
        log.info("create {} with id={}", meal, userId);
        checkNew(meal);
        return service.create(meal, userId);
    }

    public void update(Meal meal, int userId) {
        log.info("update {} with id={}", meal, userId);
        assureIdConsistent(meal, userId);
        service.update(meal, userId);
    }

    public void delete(int id, int userId) {
        log.info("delete {}", id);
        service.delete(id, userId);
    }

    public Meal get(int id, int userId) {
        log.info("get {}", id);
        return service.get(id, userId);
    }

    public List<Meal> getAll(int userId) {
        log.info("getAll");
        return service.getAll(userId);
    }
}
