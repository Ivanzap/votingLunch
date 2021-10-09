package ru.ivanzap.votinglunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ivanzap.votinglunch.model.Dish;
import ru.ivanzap.votinglunch.testdata.DishTestData;
import ru.ivanzap.votinglunch.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.ivanzap.votinglunch.testdata.DishTestData.*;
import static ru.ivanzap.votinglunch.testdata.RestaurantTestData.RESTAURANT_ID1;

public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    DishService service;

    @Test
    public void create() {
        Dish created = service.create(DishTestData.getNew(), RESTAURANT_ID1);
        int newId = created.getId();
        Dish newDish = DishTestData.getNew();
        newDish.setId(newId);
        MATCHER.assertMatch(created, newDish);
        MATCHER.assertMatch(service.get(newId, RESTAURANT_ID1), newDish);
    }

    @Test
    public void update() {
        Dish updated = DishTestData.getUpdated();
        service.update(updated, RESTAURANT_ID1);
        MATCHER.assertMatch(service.get(DISH_TODAY_RES1_ID1, RESTAURANT_ID1), DishTestData.getUpdated());
    }

    @Test
    public void delete() {
        service.delete(DISH_TODAY_RES1_ID1, RESTAURANT_ID1);
        assertThrows(NotFoundException.class, () -> service.get(DISH_TODAY_RES1_ID1, RESTAURANT_ID1));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, RESTAURANT_ID1));
    }

    @Test
    public void get() {
        Dish dish = service.get(DISH_TODAY_RES1_ID1, RESTAURANT_ID1);
        MATCHER.assertMatch(dish, DishTestData.DISH_TODAY_RES_1_1);
    }

    @Test
    public void getAll() {
        List<Dish> allMenuRes = service.getAll(RESTAURANT_ID1);
        MATCHER.assertMatch(allMenuRes, DishTestData.dishes);
    }

    @Test
    public void getFilter() {
        List<Dish> menuToday = service.getFilter(RESTAURANT_ID1, TODAY);
        MATCHER.assertMatch(menuToday, DISH_TODAY_RES_1_2, DISH_TODAY_RES_1_1, DISH_TODAY_RES_1_3);
    }

    @Test
    public void getToday() {
        List<Dish> menuToday = service.getToday(RESTAURANT_ID1);
        MATCHER.assertMatch(menuToday, DISH_TODAY_RES_1_2, DISH_TODAY_RES_1_1, DISH_TODAY_RES_1_3);
    }
}