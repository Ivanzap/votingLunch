package ru.javaOps.votingLunch.service;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaOps.votingLunch.VoteTestData;
import ru.javaOps.votingLunch.model.Vote;
import ru.javaOps.votingLunch.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javaOps.votingLunch.RestaurantTestData.RESTAURANT_ID1;
import static ru.javaOps.votingLunch.RestaurantTestData.RESTAURANT_ID2;
import static ru.javaOps.votingLunch.UserTestData.ADMIN_ID;
import static ru.javaOps.votingLunch.UserTestData.USER_ID1;
import static ru.javaOps.votingLunch.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    VoteService service;

    @Test
    public void create() {
        Vote created = service.create(VoteTestData.getNew(), USER_ID1, RESTAURANT_ID2);
        int newId = created.getId();
        Vote newVote = VoteTestData.getNew();
        newVote.setId(newId);
        MATCHER.assertMatch(created, newVote);
        MATCHER.assertMatch(service.get(newId, USER_ID1), newVote);
    }

    @Test
    public void createAfterTime() {
        Vote created = service.create(VoteTestData.getNewAfterTime(), USER_ID1, RESTAURANT_ID2);
        MATCHER.assertMatch(created, null);
    }

    @Test
    public void update() {
        Vote updated = VoteTestData.getUpdated();
        service.update(updated, USER_ID1, RESTAURANT_ID1);
        MATCHER.assertMatch(service.get(VOTE_USER1, USER_ID1), VoteTestData.getUpdated());
    }

    @Test
    public void updateAfterTime() {
        Vote updated = VoteTestData.getUpdatedAfterTime();
        assertThrows(NotFoundException.class, () -> service.update(updated, USER_ID1, RESTAURANT_ID1));
    }

    @Test
    public void delete() {
        service.delete(VOTE_USER1, USER_ID1);
        assertThrows(NotFoundException.class, () -> service.get(VOTE_USER1, USER_ID1));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(VoteTestData.NOT_FOUND, ADMIN_ID));
    }

    @Test
    public void deleteNotOwn() {
        assertThrows(NotFoundException.class, () -> service.delete(VOTE_ADMIN, USER_ID1));
    }

    @Test
    public void get() {
        Vote vote = service.get(VOTE_USER1, USER_ID1);
        MATCHER.assertMatch(vote, voteUser1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, ADMIN_ID));
    }

    @Test
    public void getNotOwn() {
        assertThrows(NotFoundException.class, () -> service.get(VOTE_ADMIN, USER_ID1));
    }

    @Test
    public void getAll() {
        List<Vote> all = service.getAll(USER_ID1);
        MATCHER.assertMatch(all, voteUser1);
    }
    
    @Test
    public void getAllVotesToday() {
        List<Vote> allToday = service.getAllVotesToday(TODAY);
        MATCHER.assertMatch(allToday, votesToday);
    }
}