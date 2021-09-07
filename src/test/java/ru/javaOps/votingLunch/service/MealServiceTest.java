package ru.javaOps.votingLunch.service;

import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javaOps.votingLunch.MealTestData;
import ru.javaOps.votingLunch.TimingRules;
import ru.javaOps.votingLunch.model.Meal;
import ru.javaOps.votingLunch.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javaOps.votingLunch.MealTestData.*;
import static ru.javaOps.votingLunch.RestaurantTestData.RESTAURANT_ID1;
import static ru.javaOps.votingLunch.UserTestData.ADMIN_ID;
import static ru.javaOps.votingLunch.UserTestData.NOT_FOUND;

@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
public class MealServiceTest {

    @ClassRule
    public static ExternalResource summary = TimingRules.SUMMARY;

    @Rule
    public Stopwatch stopwatch = TimingRules.STOPWATCH;

    @Autowired
    MealService service;

    @Test
    public void create() {
        Meal created = service.create(getNew(), ADMIN_ID);
        int newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        MATCHER.assertMatch(created, newMeal);
        MATCHER.assertMatch(service.get(newId), newMeal);
    }

    @Ignore
    @Test
    public void nonAdminCreate() {
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, ADMIN_ID);
        MATCHER.assertMatch(service.get(MEAL_TODAY_RES1_ID1), getUpdated());
    }

    @Ignore
    @Test
    public void nonAdminUpdate() {
    }

    @Test
    public void delete() {
        service.delete(MEAL_TODAY_RES1_ID1, ADMIN_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL_TODAY_RES1_ID1));
    }

    @Ignore
    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, ADMIN_ID));
    }

    @Ignore
    @Test
    public void nonAdminDelete() {
    }

    @Test
    public void get() {
        Meal meal = service.get(MEAL_TODAY_RES1_ID1);
        MATCHER.assertMatch(meal, MealTestData.mealTodayRes1_1);
    }

    @Ignore
    @Test
    public void getAll() {
    }

    @Test
    public void getAllMenuOfRestaurant() {
        List<Meal> allMenuRes = service.getAllMenuOfRestaurant(RESTAURANT_ID1);
        MATCHER.assertMatch(allMenuRes, MealTestData.meals);
    }

    @Test
    public void getMenuTodayOfRestaurant() {
        List<Meal> menuToday = service.getMenuTodayOfRestaurant(RESTAURANT_ID1, TODAY);
        MATCHER.assertMatch(menuToday, mealTodayRes1_1, mealTodayRes1_2, mealTodayRes1_3);
    }
}