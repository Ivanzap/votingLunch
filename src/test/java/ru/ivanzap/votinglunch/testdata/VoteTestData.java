package ru.ivanzap.votinglunch.testdata;

import ru.ivanzap.votinglunch.MatcherFactory;
import ru.ivanzap.votinglunch.model.Vote;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.ivanzap.votinglunch.model.AbstractBaseEntity.START_SEQ;
import static ru.ivanzap.votinglunch.testdata.RestaurantTestData.res1;
import static ru.ivanzap.votinglunch.testdata.RestaurantTestData.res2;
import static ru.ivanzap.votinglunch.testdata.UserTestData.user1;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "restaurant", "user");
    public static final LocalTime DEADLINE = LocalTime.of(11, 0);

    public static final int VOTE_USER1 = START_SEQ + 31;
    public static final int VOTE_USER2 = START_SEQ + 32;
    public static final int VOTE_ADMIN = START_SEQ + 33;
    public static final int NOT_FOUND = 10;

    public static final Vote voteUser1 = new Vote(VOTE_USER1, user1, res2,
            LocalDate.now(), LocalTime.of(9, 0));

    public static Vote getNew() {
        return new Vote(null, null, null,
                LocalDate.now(), LocalTime.of(10, 20));
    }

    public static Vote getNewAfterTime() {
        return new Vote(null, user1, res2,
                LocalDate.now(), LocalTime.of(11, 20));
    }

    public static Vote getUpdated() {
        Vote updated = new Vote(voteUser1);
        updated.setRestaurant(res1);
        updated.setDate(LocalDate.now());
        updated.setTime(LocalTime.of(9, 20));
        return updated;
    }

    public static Vote getUpdatedAfterTime() {
        Vote updated = new Vote(voteUser1);
        updated.setRestaurant(res1);
        updated.setDate(LocalDate.now());
        updated.setTime(LocalTime.of(11, 20));
        return updated;
    }
}
