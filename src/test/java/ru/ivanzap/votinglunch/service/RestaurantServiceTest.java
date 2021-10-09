package ru.ivanzap.votinglunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.ivanzap.votinglunch.model.Restaurant;
import ru.ivanzap.votinglunch.testdata.RestaurantTestData;
import ru.ivanzap.votinglunch.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.ivanzap.votinglunch.testdata.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    RestaurantService service;

    @Test
    public void create() {
        Restaurant created = service.create(getNew());
        int newId = created.getId();
        Restaurant newRes = getNew();
        newRes.setId(newId);
        MATCHER.assertMatch(created, newRes);
        MATCHER.assertMatch(service.get(newId), newRes);
    }

    @Test
    public void duplicateNameCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Restaurant(null, "Victoria")));
    }

    @Test
    public void update() {
        Restaurant updated = getUpdated();
        service.update(updated);
        MATCHER.assertMatch(service.get(RESTAURANT_ID1), getUpdated());
    }

    @Test
    public void delete() {
        service.delete(RESTAURANT_ID1);
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT_ID1));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(RestaurantTestData.NOT_FOUND));
    }

    @Test
    public void get() {
        Restaurant restaurant = service.get(RESTAURANT_ID1);
        MATCHER.assertMatch(restaurant, RestaurantTestData.res1);
    }

    @Test
    public void getAll() {
        List<Restaurant> all = service.getAll();
        MATCHER.assertMatch(all, res3, res4, res1, res2);
    }

    @Test
    public void createWithException() throws Exception {
        validateRootCause(() -> service.create(new Restaurant(null, "  ")), ConstraintViolationException.class);
    }

    @Test
    public void getWithDishes() throws Exception {
        Restaurant restaurant = service.getWithDishes(RESTAURANT_ID1);
        MATCHER.assertMatch(restaurant, res1);
    }

    @Test
    public void getWithDishesNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.getWithDishes(1));
    }
}