package ru.ivanzap.votinglunch.testdata;

import ru.ivanzap.votinglunch.MatcherFactory;
import ru.ivanzap.votinglunch.model.Role;
import ru.ivanzap.votinglunch.model.User;
import ru.ivanzap.votinglunch.model.Vote;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.ivanzap.votinglunch.model.AbstractBaseEntity.START_SEQ;
import static ru.ivanzap.votinglunch.testdata.RestaurantTestData.res2;
import static ru.ivanzap.votinglunch.testdata.RestaurantTestData.res3;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "registered", "roles", "votes", "password");
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

    public static final Vote voteUser1 = new Vote(VoteTestData.VOTE_USER1, user1, res2,
            LocalDate.now(), LocalTime.of(9, 0));
    public static final Vote voteUser2 = new Vote(VoteTestData.VOTE_USER2, user2, res2,
            LocalDate.now(), LocalTime.of(10, 0));
    public static final Vote voteAdmin = new Vote(VoteTestData.VOTE_ADMIN, admin, res3,
            LocalDate.now(), LocalTime.of(9, 0));

    static {
        user1.setVotes(List.of(voteUser1));
        user2.setVotes(List.of(voteUser2));
        admin.setVotes(List.of(voteAdmin));
    }

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass",
                false, new Date(), Collections.singleton(Role.USER));
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
}
