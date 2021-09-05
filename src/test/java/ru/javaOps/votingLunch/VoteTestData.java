package ru.javaOps.votingLunch;

import ru.javaOps.votingLunch.model.Vote;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javaOps.votingLunch.RestaurantTestData.*;
import static ru.javaOps.votingLunch.UserTestData.*;
import static ru.javaOps.votingLunch.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final int VOTE_USER1 = START_SEQ + 31;
    public static final int VOTE_USER2 = START_SEQ + 32;
    public static final int VOTE_ADMIN = START_SEQ + 33;

    public static final Vote voteUser1 = new Vote(VOTE_USER1, user1, res2,
            LocalDateTime.of(2021, 7, 2, 11, 15, 24));
    public static final Vote voteUser2 = new Vote(VOTE_USER2, user2, res2,
            LocalDateTime.of(2021, 7, 2, 11, 24, 12));
    public static final Vote voteAdmin = new Vote(VOTE_ADMIN, admin, res3,
            LocalDateTime.of(2021, 7, 2, 10, 39, 44));

    public static Vote getNew() {
        return new Vote(null, user1, res2,
                LocalDateTime.of(2021, 7, 2, 11, 35, 0));
    }

    public static Vote getUpdated() {
        Vote updated = new Vote(voteUser1);
        updated.setRestaurant(res1);
        updated.setDateTime(LocalDateTime.of(2021, 7, 2, 11, 17, 30));
        return updated;
    }

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("restaurant", "user").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "user").isEqualTo(expected);
    }
}
