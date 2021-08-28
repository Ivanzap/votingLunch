package ru.javaOps.votingLunch.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javaOps.votingLunch.UserTestData;
import ru.javaOps.votingLunch.model.User;

import static ru.javaOps.votingLunch.UserTestData.USER_ID1;
import static ru.javaOps.votingLunch.UserTestData.assertMatch;

@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
//        User user = service.get(USER_ID1);
//        assertMatch(user, UserTestData.user1);
    }

    @Test
    public void getByEmail() {
    }

    @Test
    public void getAll() {
    }
}