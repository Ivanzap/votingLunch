package ru.ivanzap.votinglunch.web.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ivanzap.votinglunch.model.User;
import ru.ivanzap.votinglunch.service.UserService;
import ru.ivanzap.votinglunch.testdata.UserTestData;
import ru.ivanzap.votinglunch.util.exception.NotFoundException;
import ru.ivanzap.votinglunch.web.AbstractControllerTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ivanzap.votinglunch.TestUtil.userHttpBasic;
import static ru.ivanzap.votinglunch.testdata.UserTestData.jsonWithPassword;

public class AdminRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestController.REST_URL + '/';

    @Autowired
    UserService service;

    @Test
    public void createWithLocation() throws Exception {
        User newUser = UserTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(UserTestData.admin))
                .content(jsonWithPassword(newUser, newUser.getPassword())))
                .andExpect(status().isCreated());

        User created = UserTestData.MATCHER.readFromJson(action);
        int newId = created.id();
        newUser.setId(newId);
        UserTestData.MATCHER.assertMatch(created, newUser);
        UserTestData.MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    public void update() throws Exception {
        User updated = UserTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + UserTestData.USER_ID1)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(UserTestData.admin))
                .content(jsonWithPassword(updated, updated.getPassword())))
                .andExpect(status().isNoContent());

        UserTestData.MATCHER.assertMatch(service.get(UserTestData.USER_ID1), UserTestData.getUpdated());
    }

    @Test
    public void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + UserTestData.USER_ID1)
                .with(userHttpBasic(UserTestData.admin)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> service.get(UserTestData.USER_ID1));
    }

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + UserTestData.USER_ID1)
                .with(userHttpBasic(UserTestData.admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(UserTestData.MATCHER.contentJson(UserTestData.user1));
    }

    @Test
    public void getByEmail() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "by?email=" + UserTestData.user1.getEmail())
                .with(userHttpBasic(UserTestData.admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(UserTestData.MATCHER.contentJson(UserTestData.user1));
    }

    @Test
    public void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(UserTestData.admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(UserTestData.MATCHER.contentJson(UserTestData.admin, UserTestData.user1, UserTestData.user2));
    }

    @Test
    public void getWithVotes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + UserTestData.USER_ID1 + "/with-votes")
                .with(userHttpBasic(UserTestData.admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(UserTestData.VOTES_MATCHER.contentJson(UserTestData.user1));
    }

    @Test
    public void enable() throws Exception {
        perform(MockMvcRequestBuilders.patch(REST_URL + UserTestData.USER_ID1)
                .param("enabled", "false")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(UserTestData.admin)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertFalse(service.get(UserTestData.USER_ID1).isEnabled());
    }
}
