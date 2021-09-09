package ru.javaOps.votingLunch;

import ru.javaOps.votingLunch.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javaOps.votingLunch.RestaurantTestData.*;
import static ru.javaOps.votingLunch.UserTestData.*;
import static ru.javaOps.votingLunch.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final MatcherFactory<Vote> MATCHER = MatcherFactory.usingIgnoringFieldsComparator("restaurant", "user");
    public static final LocalDateTime TODAY = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);

    public static final int VOTE_USER1 = START_SEQ + 31;
    public static final int VOTE_USER2 = START_SEQ + 32;
    public static final int VOTE_ADMIN = START_SEQ + 33;
    public static final int NOT_FOUND = 10;

    public static final Vote voteUser1 = new Vote(VOTE_USER1, user1, res2,
            LocalDate.now().atTime(9, 0));
    public static final Vote voteUser2 = new Vote(VOTE_USER2, user2, res2,
            LocalDate.now().atTime(10, 0));
    public static final Vote voteAdmin = new Vote(VOTE_ADMIN, admin, res3,
            LocalDate.now().atTime(9, 0));

    public static List<Vote> votesToday = List.of(voteUser2, voteAdmin, voteUser1);

    public static Vote getNew() {
        return new Vote(null, user1, res2,
                LocalDate.now().atTime(10, 20));
    }

    public static Vote getUpdated() {
        Vote updated = new Vote(voteUser1);
        updated.setRestaurant(res1);
        updated.setDateTime(LocalDate.now().atTime(9, 20));
        return updated;
    }
}
