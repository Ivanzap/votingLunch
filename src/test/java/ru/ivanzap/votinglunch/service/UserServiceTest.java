package ru.ivanzap.votinglunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.ivanzap.votinglunch.model.Role;
import ru.ivanzap.votinglunch.model.User;
import ru.ivanzap.votinglunch.testdata.UserTestData;
import ru.ivanzap.votinglunch.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void create() {
        User created = service.create(UserTestData.getNew());
        Integer newId = created.getId();
        User newUser = UserTestData.getNew();
        newUser.setId(newId);
        UserTestData.MATCHER.assertMatch(created, newUser);
        UserTestData.MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    public void update() {
        User updated = UserTestData.getUpdated();
        service.update(updated);
        UserTestData.MATCHER.assertMatch(service.get(UserTestData.USER_ID1), UserTestData.getUpdated());
    }

    @Test
    public void delete() {
        service.delete(UserTestData.USER_ID1);
        assertThrows(NotFoundException.class, () -> service.get(UserTestData.USER_ID1));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(UserTestData.NOT_FOUND));
    }

    @Test
    public void get() {
        User user = service.get(UserTestData.USER_ID1);
        UserTestData.MATCHER.assertMatch(user, UserTestData.user1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(UserTestData.NOT_FOUND));
    }

    @Test
    public void getByEmail() {
        User user = service.getByEmail("admin@gmail.com");
        UserTestData.MATCHER.assertMatch(user, UserTestData.admin);
    }

    @Test
    public void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.USER)));
    }

    @Test
    public void getAll() {
        List<User> all = service.getAll();
        UserTestData.MATCHER.assertMatch(all, UserTestData.admin, UserTestData.user1, UserTestData.user2);
    }

    @Test
    public void createWithException() throws Exception {
        validateRootCause(() -> service.create(new User(null, "  ", "user@yandex.ru", "password", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User 1", "  ", "password", Role.USER)), ConstraintViolationException.class);
    }

    @Test
    public void getWithVotes() {
        User user1WithVotes = service.getWithVotes(UserTestData.USER_ID1);
        UserTestData.VOTES_MATCHER.assertMatch(user1WithVotes, UserTestData.user1);
    }
}