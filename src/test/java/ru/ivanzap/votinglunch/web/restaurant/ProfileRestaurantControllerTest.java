package ru.ivanzap.votinglunch.web.restaurant;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ivanzap.votinglunch.service.RestaurantService;
import ru.ivanzap.votinglunch.testdata.RestaurantTestData;
import ru.ivanzap.votinglunch.testdata.UserTestData;
import ru.ivanzap.votinglunch.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ivanzap.votinglunch.TestUtil.userHttpBasic;
import static ru.ivanzap.votinglunch.testdata.RestaurantTestData.*;

public class ProfileRestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ProfileRestaurantController.REST_URL + '/';

    @Autowired
    RestaurantService service;

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RestaurantTestData.RESTAURANT_ID1)
                .with(userHttpBasic(UserTestData.user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RestaurantTestData.MATCHER.contentJson(RestaurantTestData.res1));
    }

    @Test
    public void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RestaurantTestData.RESTAURANT_ID1))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    public void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(UserTestData.user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RestaurantTestData.MATCHER.contentJson(RestaurantTestData.res3,
                        RestaurantTestData.res4,
                        RestaurantTestData.res1,
                        RestaurantTestData.res2));
    }

    @Test
    public void getWithDishes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RestaurantTestData.RESTAURANT_ID1 + "/with-dishes")
                .with(userHttpBasic(UserTestData.user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RestaurantTestData.WITH_DISHES_MATCHER.contentJson(RestaurantTestData.res1));
    }

    @Test
    public void getAllWithDishes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/all-with-dishes")
                .with(userHttpBasic(UserTestData.user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RestaurantTestData.WITH_DISHES_MATCHER.contentJson(res1, res2, res3, res4));
    }
}