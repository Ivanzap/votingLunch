package ru.ivanzap.votinglunch.web.vote;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ivanzap.votinglunch.model.Vote;
import ru.ivanzap.votinglunch.service.VoteService;
import ru.ivanzap.votinglunch.testdata.RestaurantTestData;
import ru.ivanzap.votinglunch.testdata.UserTestData;
import ru.ivanzap.votinglunch.testdata.VoteTestData;
import ru.ivanzap.votinglunch.util.exception.NotFoundException;
import ru.ivanzap.votinglunch.web.AbstractControllerTest;
import ru.ivanzap.votinglunch.web.json.JsonUtil;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ivanzap.votinglunch.TestUtil.userHttpBasic;
import static ru.ivanzap.votinglunch.testdata.RestaurantTestData.*;

public class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteController.REST_URL + '/';

    @Autowired
    VoteService service;

    @Test
    public void createWithLocation() throws Exception {
        Vote newVote = VoteTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "restaurants/" + RESTAURANT_ID2)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(UserTestData.user2))
                .content(JsonUtil.writeValue(newVote)));

        Vote created = VoteTestData.MATCHER.readFromJson(action);
        int newId = created.getId();
        newVote.setId(newId);
        VoteTestData.MATCHER.assertMatch(created, newVote);
        VoteTestData.MATCHER.assertMatch(service.get(newId, UserTestData.USER_ID2), newVote);
    }

    @Test
    public void update() throws Exception {
        Vote updated = VoteTestData.getUpdated();
        if (LocalTime.now().isBefore(VoteTestData.DEADLINE)) {
            perform(MockMvcRequestBuilders.put(REST_URL + VoteTestData.VOTE_ID_USER_1_TODAY + "/" + RestaurantTestData.RESTAURANT_ID1)
                    .contentType(MediaType.APPLICATION_JSON)
                    .with(userHttpBasic(UserTestData.user1))
                    .content(JsonUtil.writeValue(updated)))
                    .andExpect(status().isNoContent());
            VoteTestData.MATCHER.assertMatch(service.get(VoteTestData.VOTE_ID_USER_1_TODAY, UserTestData.USER_ID1), VoteTestData.getUpdated());
        } else {
            assertThrows(IllegalArgumentException.class, () -> service.update(updated, UserTestData.USER_ID1, RESTAURANT_ID1));
        }
    }

    @Test
    public void delete() throws Exception {
        if (LocalTime.now().isBefore(VoteTestData.DEADLINE)) {
            perform(MockMvcRequestBuilders.delete(REST_URL + VoteTestData.VOTE_ID_USER_1_YESTERDAY)
                    .with(userHttpBasic(UserTestData.user1)))
                    .andExpect(status().isNoContent());
            assertThrows(NotFoundException.class, () -> service.get(VoteTestData.VOTE_ID_USER_1_YESTERDAY, UserTestData.USER_ID1));
        } else {
            assertThrows(IllegalArgumentException.class, () -> service.delete(VoteTestData.VOTE_ID_USER_1_YESTERDAY, UserTestData.USER_ID1));
        }
    }

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + VoteTestData.VOTE_ID_USER_1_YESTERDAY)
                .with(userHttpBasic(UserTestData.user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VoteTestData.MATCHER.contentJson(VoteTestData.VOTE_USER_1_YESTERDAY));
    }

    @Test
    public void getToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "today")
                .with(userHttpBasic(UserTestData.user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VoteTestData.MATCHER.contentJson(VoteTestData.VOTE_USER_1_TODAY));
    }

    @Test
    public void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(UserTestData.user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VoteTestData.MATCHER.contentJson(VoteTestData.VOTE_USER_1_TODAY, VoteTestData.VOTE_USER_1_YESTERDAY));
    }
}