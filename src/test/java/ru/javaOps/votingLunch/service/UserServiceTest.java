package ru.javaOps.votingLunch.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import ru.javaOps.votingLunch.UserTestData;
import ru.javaOps.votingLunch.model.Role;
import ru.javaOps.votingLunch.model.User;
import ru.javaOps.votingLunch.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertThrows;
import static ru.javaOps.votingLunch.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService service;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        Objects.requireNonNull(cacheManager.getCache("users")).clear();
    }

    @Test
    public void create() {
        User created = service.create(getNew());
        Integer newId = created.getId();
        User newUser = getNew();
        newUser.setId(newId);
        MATCHER.assertMatch(created, newUser);
        MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    public void update() {
        User updated = getUpdated();
        service.update(updated);
        MATCHER.assertMatch(service.get(USER_ID1), getUpdated());
    }

    @Test
    public void delete() {
        service.delete(USER_ID1);
        assertThrows(NotFoundException.class, () -> service.get(USER_ID1));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    public void get() {
        User user = service.get(USER_ID1);
        MATCHER.assertMatch(user, UserTestData.user1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    public void getByEmail() {
        User user = service.getByEmail("admin@gmail.com");
        MATCHER.assertMatch(user, admin);
    }

    @Test
    public void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.USER)));
    }

    @Test
    public void getAll() {
        List<User> all = service.getAll();
        MATCHER.assertMatch(all, admin, user1, user2);
    }

    @Test
    public void createWithException() throws Exception {
        validateRootCause(() -> service.create(new User(null, "  ", "user@yandex.ru", "password", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User 1", "  ", "password", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User 1", "user@yandex.ru", "  ", Role.USER)), ConstraintViolationException.class);
    }
}