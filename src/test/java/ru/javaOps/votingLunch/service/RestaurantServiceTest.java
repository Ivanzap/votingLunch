package ru.javaOps.votingLunch.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javaOps.votingLunch.RestaurantTestData;
import ru.javaOps.votingLunch.model.Restaurant;
import ru.javaOps.votingLunch.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.*;
import static ru.javaOps.votingLunch.RestaurantTestData.*;
import static ru.javaOps.votingLunch.UserTestData.ADMIN_ID;
import static ru.javaOps.votingLunch.UserTestData.USER_ID1;

@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
public class RestaurantServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    RestaurantService service;

    @Test
    public void create() {
        Restaurant created = service.create(getNew(), ADMIN_ID);
        int newId = created.getId();
        Restaurant newRes = getNew();
        newRes.setId(newId);
        assertMatch(created, newRes);
        assertMatch(service.get(newId), newRes);
    }

    @Ignore
    @Test
    public void nonAdminCreate() {
        Restaurant created = service.create(getNew(), USER_ID1);
        assertMatch(created, null);
    }

    @Test
    public void update() {
        Restaurant updated = getUpdated();
        service.update(updated, ADMIN_ID);
        assertMatch(service.get(RESTAURANT_ID1), getUpdated());
    }

    @Ignore
    @Test
    public void nonAdminUpdate() {}

    @Test
    public void delete() {
        service.delete(RESTAURANT_ID1, ADMIN_ID);
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT_ID1));
    }

    @Ignore
    @Test
    public void nonAdminDelete() {}

    @Test
    public void get() {
        Restaurant restaurant = service.get(RESTAURANT_ID1);
        assertMatch(restaurant, RestaurantTestData.res1);
    }

    @Test
    public void getAll() {
        List<Restaurant> all = service.getAll();
        assertMatch(all, res3, res4, res1, res2);
    }
}