package ru.ivanzap.votinglunch.web.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ivanzap.votinglunch.model.User;
import ru.ivanzap.votinglunch.service.UserService;
import ru.ivanzap.votinglunch.testdata.UserTestData;
import ru.ivanzap.votinglunch.to.UserTo;
import ru.ivanzap.votinglunch.util.UserUtil;
import ru.ivanzap.votinglunch.web.AbstractControllerTest;
import ru.ivanzap.votinglunch.web.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ivanzap.votinglunch.TestUtil.userHttpBasic;

public class ProfileRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ProfileRestController.REST_URL + '/';

    @Autowired
    UserService service;

    @Test
    public void register() throws Exception {
        UserTo newTo = UserTestData.getNewTo();
        User newUser = UserUtil.createNewFromTo(newTo);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andDo(print())
                .andExpect(status().isCreated());

        User created = UserTestData.MATCHER.readFromJson(action);
        int newId = created.id();
        newUser.setId(newId);
        UserTestData.MATCHER.assertMatch(created, newUser);
        UserTestData.MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    public void update() throws Exception {
        UserTo updatedTo = UserTestData.getUpdatedTo();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(UserTestData.user1))
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isNoContent());

        UserTestData.MATCHER.assertMatch(service.get(UserTestData.USER_ID1), UserUtil.updateFromTo(new User(UserTestData.user1), updatedTo));
    }

    @Test
    public void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL)
                .with(userHttpBasic(UserTestData.user1)))
                .andDo(print())
                .andExpect(status().isNoContent());
        UserTestData.MATCHER.assertMatch(service.getAll(), UserTestData.admin, UserTestData.user2);
    }

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(UserTestData.user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(UserTestData.MATCHER.contentJson(UserTestData.user1));
    }

    @Test
    public void getWithVotes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/with-votes")
                .with(userHttpBasic(UserTestData.user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(UserTestData.VOTES_MATCHER.contentJson(UserTestData.user1));
    }
}
