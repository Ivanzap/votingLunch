package ru.ivanzap.votinglunch.testdata;

import ru.ivanzap.votinglunch.MatcherFactory;
import ru.ivanzap.votinglunch.model.Vote;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.ivanzap.votinglunch.model.AbstractBaseEntity.START_SEQ;
import static ru.ivanzap.votinglunch.testdata.RestaurantTestData.*;
import static ru.ivanzap.votinglunch.testdata.UserTestData.*;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "restaurant", "user", "time");
    public static final LocalTime DEADLINE = LocalTime.of(11, 0);

    public static final int VOTE_ID_USER_1_YESTERDAY = START_SEQ + 31;
    public static final int VOTE_ID_ADMIN_YESTERDAY = START_SEQ + 32;
    public static final int VOTE_ID_USER_2_YESTERDAY = START_SEQ + 33;
    public static final int VOTE_ID_USER_1_TODAY = START_SEQ + 34;
    public static final int VOTE_ID_ADMIN_TODAY = START_SEQ + 35;
    public static final int NOT_FOUND = 10;

    public static final Vote VOTE_USER_1_YESTERDAY = new Vote(VOTE_ID_USER_1_YESTERDAY, user1, res1,
            LocalDate.now().minusDays(1), LocalTime.of(9, 40));
    public static final Vote VOTE_USER_1_TODAY = new Vote(VOTE_ID_USER_1_TODAY, user1, res2,
            LocalDate.now(), LocalTime.of(9, 0));
    public static final Vote VOTE_USER_2_YESTERDAY = new Vote(VOTE_ID_USER_2_YESTERDAY, user2, res2,
            LocalDate.now().minusDays(1), LocalTime.of(8, 0));
    public static final Vote VOTE_ADMIN_YESTERDAY = new Vote(VOTE_ID_ADMIN_YESTERDAY, admin, res1,
            LocalDate.now().minusDays(1), LocalTime.of(8, 35));
    public static final Vote VOTE_ADMIN_TODAY = new Vote(VOTE_ID_ADMIN_TODAY, admin, res3,
            LocalDate.now(), LocalTime.of(9, 30));

    public static Vote getNew() {
        return new Vote(null, null, null,
                LocalDate.now(), LocalTime.now());
    }

    public static Vote getUpdated() {
        Vote updated = new Vote(VOTE_USER_1_YESTERDAY);
        updated.setRestaurant(res1);
        updated.setDate(LocalDate.now());
        updated.setTime(LocalTime.of(9, 20));
        return updated;
    }
}
