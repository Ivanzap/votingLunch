package ru.javaOps.votingLunch;

import ru.javaOps.votingLunch.model.Role;
import ru.javaOps.votingLunch.model.User;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javaOps.votingLunch.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID1 = START_SEQ;
    public static final int USER_ID2 = START_SEQ + 1;
    public static final int ADMIN_ID = START_SEQ + 2;
    public static final int NOT_FOUND = 10;

    public static final User user1 = new User(USER_ID1, "User 1",
            "user@yandex.ru", "password", Role.USER);

    public static final User user2 = new User(USER_ID2, "User 2",
            "user2@yandex.ru", "password", Role.USER);

    public static final User admin = new User(ADMIN_ID, "Admin",
            "admin@gmail.com", "admin", Role.ADMIN, Role.USER);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("registered", "roles").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }
}
