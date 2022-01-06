package ru.ivanzap.votinglunch.testdata;

import ru.ivanzap.votinglunch.MatcherFactory;
import ru.ivanzap.votinglunch.model.Role;
import ru.ivanzap.votinglunch.model.User;
import ru.ivanzap.votinglunch.to.UserTo;
import ru.ivanzap.votinglunch.web.json.JsonUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.ivanzap.votinglunch.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "registered", "votes", "password");
    public static final MatcherFactory.Matcher<User> VOTES_MATCHER =
            MatcherFactory.usingAssertions(User.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("votes.user", "votes.restaurant", "password", "registered")
                            .ignoringAllOverriddenEquals().isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

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

    static {
        user1.setVotes(Arrays.asList(VoteTestData.VOTE_USER_1_YESTERDAY, VoteTestData.VOTE_USER_1_TODAY));
        user2.setVotes(Arrays.asList(VoteTestData.VOTE_USER_2_YESTERDAY));
        admin.setVotes(Arrays.asList(VoteTestData.VOTE_ADMIN_YESTERDAY, VoteTestData.VOTE_ADMIN_TODAY));
    }

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass",
                false, new Date(), Collections.singleton(Role.USER));
    }

    public static UserTo getNewTo() {
        return new UserTo(null, "newName", "newemail@ya.ru", "newPassword");
    }

    public static User getUpdated() {
        User updated = new User(user1);
        updated.setEmail("update@gmail.com");
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        updated.setEnabled(false);
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }

    public static UserTo getUpdatedTo() {
        return new UserTo(null, "Update Name", user1.getEmail(), "newPass");
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
