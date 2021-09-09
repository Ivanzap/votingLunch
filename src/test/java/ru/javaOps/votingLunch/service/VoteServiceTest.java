package ru.javaOps.votingLunch.service;

import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javaOps.votingLunch.TimingRules;
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

@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
public class VoteServiceTest {

    @ClassRule
    public static ExternalResource summary = TimingRules.SUMMARY;

    @Rule
    public Stopwatch stopwatch = TimingRules.STOPWATCH;

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
    public void update() {
        Vote updated = VoteTestData.getUpdated();
        service.update(updated, USER_ID1, RESTAURANT_ID1);
        MATCHER.assertMatch(service.get(VOTE_USER1, USER_ID1), VoteTestData.getUpdated());
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
    public void get() {
        Vote vote = service.get(VOTE_USER1, USER_ID1);
        MATCHER.assertMatch(vote, voteUser1);
    }

    @Test
    public void getAll() {
        List<Vote> all = service.getAll(USER_ID1);
        MATCHER.assertMatch(all, voteUser1);
    }

    @Ignore
    @Test
    public void getAllVotesToday() {
        List<Vote> allToday = service.getAllVotesToday(TODAY);
        MATCHER.assertMatch(allToday, votesToday);
    }
}