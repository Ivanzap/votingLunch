package ru.ivanzap.votinglunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ivanzap.votinglunch.model.Vote;
import ru.ivanzap.votinglunch.testdata.UserTestData;
import ru.ivanzap.votinglunch.testdata.VoteTestData;
import ru.ivanzap.votinglunch.util.exception.NotFoundException;

import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.ivanzap.votinglunch.testdata.RestaurantTestData.RESTAURANT_ID1;
import static ru.ivanzap.votinglunch.testdata.RestaurantTestData.RESTAURANT_ID2;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    VoteService service;

    @Test
    public void create() {
        if (LocalTime.now().isBefore(VoteTestData.DEADLINE)) {
            Vote created = service.create(VoteTestData.getNew(), UserTestData.USER_ID2, RESTAURANT_ID2);
            int newId = created.getId();
            Vote newVote = VoteTestData.getNew();
            newVote.setId(newId);
            VoteTestData.MATCHER.assertMatch(created, newVote);
            VoteTestData.MATCHER.assertMatch(service.get(newId, UserTestData.USER_ID2), newVote);
        } else {
            assertThrows(IllegalArgumentException.class, () -> service.create(VoteTestData.getNew(), UserTestData.USER_ID2, RESTAURANT_ID2));
        }
    }

    @Test
    public void createAfterTime() {
        assertThrows(IllegalArgumentException.class, () -> service.create(VoteTestData.getNewAfterTime(), UserTestData.USER_ID1, RESTAURANT_ID2));
    }

    @Test
    public void update() {
        Vote updated = VoteTestData.getUpdated();
        if (LocalTime.now().isBefore(VoteTestData.DEADLINE)) {
            service.update(updated, UserTestData.USER_ID1, RESTAURANT_ID1);
            VoteTestData.MATCHER.assertMatch(service.get(VoteTestData.VOTE_USER1, UserTestData.USER_ID1), VoteTestData.getUpdated());
        } else {
            assertThrows(IllegalArgumentException.class, () -> service.update(updated, UserTestData.USER_ID1, RESTAURANT_ID1));
        }
    }

    @Test
    public void updateAfterTime() {
        Vote updated = VoteTestData.getUpdatedAfterTime();
        assertThrows(IllegalArgumentException.class, () -> service.update(updated, UserTestData.USER_ID1, RESTAURANT_ID1));
    }

    @Test
    public void delete() {
        if (LocalTime.now().isBefore(VoteTestData.DEADLINE)) {
            service.delete(VoteTestData.VOTE_USER1, UserTestData.USER_ID1);
            assertThrows(NotFoundException.class, () -> service.get(VoteTestData.VOTE_USER1, UserTestData.USER_ID1));
        } else {
            assertThrows(IllegalArgumentException.class, () -> service.delete(VoteTestData.VOTE_USER1, UserTestData.USER_ID1));
        }
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(VoteTestData.NOT_FOUND, UserTestData.ADMIN_ID));
    }

    @Test
    public void deleteNotOwn() {
        assertThrows(NotFoundException.class, () -> service.delete(VoteTestData.VOTE_ADMIN, UserTestData.USER_ID1));
    }

    @Test
    public void get() {
        Vote vote = service.get(VoteTestData.VOTE_USER1, UserTestData.USER_ID1);
        VoteTestData.MATCHER.assertMatch(vote, VoteTestData.voteUser1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(VoteTestData.NOT_FOUND, UserTestData.ADMIN_ID));
    }

    @Test
    public void getNotOwn() {
        assertThrows(NotFoundException.class, () -> service.get(VoteTestData.VOTE_ADMIN, UserTestData.USER_ID1));
    }

    @Test
    public void getToday() {
        Vote vote = service.getToday(UserTestData.USER_ID1);
        VoteTestData.MATCHER.assertMatch(vote, VoteTestData.voteUser1);
    }

    @Test
    public void getTodayNotFound() {
        assertThrows(NotFoundException.class, () -> service.getToday(UserTestData.USER_ID2));
    }


    @Test
    public void getAll() {
        List<Vote> all = service.getAll(UserTestData.USER_ID1);
        VoteTestData.MATCHER.assertMatch(all, VoteTestData.voteUser1);
    }
}