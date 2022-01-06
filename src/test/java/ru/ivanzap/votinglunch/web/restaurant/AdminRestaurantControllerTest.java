package ru.ivanzap.votinglunch.web.restaurant;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ivanzap.votinglunch.model.Restaurant;
import ru.ivanzap.votinglunch.service.RestaurantService;
import ru.ivanzap.votinglunch.testdata.RestaurantTestData;
import ru.ivanzap.votinglunch.testdata.UserTestData;
import ru.ivanzap.votinglunch.util.exception.NotFoundException;
import ru.ivanzap.votinglunch.web.AbstractControllerTest;
import ru.ivanzap.votinglunch.web.json.JsonUtil;

import static org.junit.Assert.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ivanzap.votinglunch.TestUtil.userHttpBasic;
import static ru.ivanzap.votinglunch.testdata.RestaurantTestData.MATCHER;
import static ru.ivanzap.votinglunch.testdata.RestaurantTestData.RESTAURANT_ID1;

public class AdminRestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestaurantController.REST_URL + '/';

    @Autowired
    RestaurantService service;

    @Test
    public void createWithLocation() throws Exception {
        Restaurant newRestaurant = RestaurantTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(UserTestData.admin))
                .content(JsonUtil.writeValue(newRestaurant)));

        Restaurant created = MATCHER.readFromJson(action);
        int newId = created.getId();
        newRestaurant.setId(newId);
        MATCHER.assertMatch(created, newRestaurant);
        MATCHER.assertMatch(service.get(newId), newRestaurant);
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = RestaurantTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + RestaurantTestData.RESTAURANT_ID1)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(UserTestData.admin))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        MATCHER.assertMatch(service.get(RestaurantTestData.RESTAURANT_ID1), RestaurantTestData.getUpdated());
    }

    @Test
    public void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RestaurantTestData.RESTAURANT_ID1)
                .with(userHttpBasic(UserTestData.admin)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> service.get(RestaurantTestData.RESTAURANT_ID1));
    }

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RestaurantTestData.RESTAURANT_ID1)
                .with(userHttpBasic(UserTestData.admin)))
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
    public void getWithDishes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RestaurantTestData.RESTAURANT_ID1 + "/with-dishes")
                .with(userHttpBasic(UserTestData.admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RestaurantTestData.WITH_DISHES_MATCHER.contentJson(RestaurantTestData.res1));
    }

    @Test
    public void getAllWithDishes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/all-with-dishes")
                .with(userHttpBasic(UserTestData.admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RestaurantTestData.WITH_DISHES_MATCHER.contentJson(RestaurantTestData.res1,
                        RestaurantTestData.res2,
                        RestaurantTestData.res3,
                        RestaurantTestData.res4));
    }

    @Test
    public void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(UserTestData.admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RestaurantTestData.MATCHER.contentJson(RestaurantTestData.res3,
                        RestaurantTestData.res4,
                        RestaurantTestData.res1,
                        RestaurantTestData.res2));
    }

    @Test
    public void createInvalid() throws Exception {
        Restaurant invalid = new Restaurant(null, null);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(UserTestData.admin)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateInvalid() throws Exception {
        Restaurant invalid = new Restaurant(RESTAURANT_ID1, null);
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_ID1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(UserTestData.admin)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}