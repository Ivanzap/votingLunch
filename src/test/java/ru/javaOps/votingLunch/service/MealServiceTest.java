package ru.javaOps.votingLunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaOps.votingLunch.MealTestData;
import ru.javaOps.votingLunch.model.Meal;
import ru.javaOps.votingLunch.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javaOps.votingLunch.MealTestData.*;
import static ru.javaOps.votingLunch.RestaurantTestData.RESTAURANT_ID1;
import static ru.javaOps.votingLunch.UserTestData.ADMIN_ID;
import static ru.javaOps.votingLunch.UserTestData.USER_ID1;

public class MealServiceTest extends AbstractServiceTest {

    @Autowired
    MealService service;

    @Test
    public void create() {
        Meal created = service.create(MealTestData.getNew(), ADMIN_ID);
        int newId = created.getId();
        Meal newMeal = MealTestData.getNew();
        newMeal.setId(newId);
        MATCHER.assertMatch(created, newMeal);
        MATCHER.assertMatch(service.get(newId), newMeal);
    }

    @Test
    public void nonAdminCreate() {
        Meal created = service.create(MealTestData.getNew(), USER_ID1);
        MATCHER.assertMatch(created, null);
    }

    @Test
    public void update() {
        Meal updated = MealTestData.getUpdated();
        service.update(updated, ADMIN_ID);
        MATCHER.assertMatch(service.get(MEAL_TODAY_RES1_ID1), MealTestData.getUpdated());
    }

    @Test
    public void nonAdminUpdate() {
        Meal updated = MealTestData.getUpdated();
        assertThrows(NotFoundException.class, () -> service.update(updated, USER_ID1));
    }

    @Test
    public void delete() {
        service.delete(MEAL_TODAY_RES1_ID1, ADMIN_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL_TODAY_RES1_ID1));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(35, ADMIN_ID));
    }

    @Test
    public void nonAdminDelete() {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL_TODAY_RES1_ID1, USER_ID1));
    }

    @Test
    public void get() {
        Meal meal = service.get(MEAL_TODAY_RES1_ID1);
        MATCHER.assertMatch(meal, MealTestData.mealTodayRes1_1);
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