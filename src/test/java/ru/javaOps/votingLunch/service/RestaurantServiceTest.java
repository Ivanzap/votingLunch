package ru.javaOps.votingLunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaOps.votingLunch.RestaurantTestData;
import ru.javaOps.votingLunch.model.Restaurant;
import ru.javaOps.votingLunch.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javaOps.votingLunch.RestaurantTestData.*;
import static ru.javaOps.votingLunch.UserTestData.ADMIN_ID;
import static ru.javaOps.votingLunch.UserTestData.USER_ID1;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    RestaurantService service;

    @Test
    public void create() {
        Restaurant created = service.create(getNew(), ADMIN_ID);
        int newId = created.getId();
        Restaurant newRes = getNew();
        newRes.setId(newId);
        MATCHER.assertMatch(created, newRes);
        MATCHER.assertMatch(service.get(newId), newRes);
    }

    @Test
    public void nonAdminCreate() {
        Restaurant created = service.create(getNew(), USER_ID1);
        MATCHER.assertMatch(created, null);
    }

    @Test
    public void update() {
        Restaurant updated = getUpdated();
        service.update(updated, ADMIN_ID);
        MATCHER.assertMatch(service.get(RESTAURANT_ID1), getUpdated());
    }

    @Test
    public void nonAdminUpdate() {
        Restaurant updated = getUpdated();
        assertThrows(NotFoundException.class, () -> service.update(updated, USER_ID1));
    }

    @Test
    public void delete() {
        service.delete(RESTAURANT_ID1, ADMIN_ID);
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT_ID1));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(RestaurantTestData.NOT_FOUND, ADMIN_ID));
    }

    @Test
    public void nonAdminDelete() {
        assertThrows(NotFoundException.class, () -> service.delete(RESTAURANT_ID1, USER_ID1));
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
}