package ru.ivanzap.votinglunch.web.dish;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ivanzap.votinglunch.model.Dish;
import ru.ivanzap.votinglunch.service.DishService;
import ru.ivanzap.votinglunch.testdata.DishTestData;
import ru.ivanzap.votinglunch.testdata.RestaurantTestData;
import ru.ivanzap.votinglunch.testdata.UserTestData;
import ru.ivanzap.votinglunch.util.exception.NotFoundException;
import ru.ivanzap.votinglunch.web.AbstractControllerTest;
import ru.ivanzap.votinglunch.web.json.JsonUtil;

import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ivanzap.votinglunch.TestUtil.userHttpBasic;
import static ru.ivanzap.votinglunch.testdata.DishTestData.DISH_TODAY_RES1_ID1;
import static ru.ivanzap.votinglunch.testdata.DishTestData.MATCHER;
import static ru.ivanzap.votinglunch.testdata.RestaurantTestData.RESTAURANT_ID1;

public class AdminDishControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminDishController.REST_URL + '/';

    @Autowired
    DishService dishService;

    @Test
    public void createWithLocation() throws Exception {
        Dish newDish = DishTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + RestaurantTestData.RESTAURANT_ID1)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(UserTestData.admin))
                .content(JsonUtil.writeValue(newDish)));

        Dish created = MATCHER.readFromJson(action);
        int newId = created.getId();
        newDish.setId(newId);
        MATCHER.assertMatch(created, newDish);
        MATCHER.assertMatch(dishService.get(newId, RESTAURANT_ID1), newDish);
    }

    @Test
    public void update() throws Exception {
        Dish updated = DishTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + RestaurantTestData.RESTAURANT_ID1 + "/" + DishTestData.DISH_TODAY_RES1_ID1)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(UserTestData.admin))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        MATCHER.assertMatch(dishService.get(DISH_TODAY_RES1_ID1, RESTAURANT_ID1), DishTestData.getUpdated());
    }

    @Test
    public void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RestaurantTestData.RESTAURANT_ID1 + "/" + DishTestData.DISH_TODAY_RES1_ID1)
                .with(userHttpBasic(UserTestData.admin)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> dishService.get(DishTestData.DISH_TODAY_RES1_ID1, RestaurantTestData.RESTAURANT_ID1));
    }

    @Test
    public void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RestaurantTestData.RESTAURANT_ID1 + "/" + DishTestData.NOT_FOUND)
                .with(userHttpBasic(UserTestData.admin)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RestaurantTestData.RESTAURANT_ID1 + "/" + DishTestData.DISH_TODAY_RES1_ID1)
                .with(userHttpBasic(UserTestData.admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DishTestData.MATCHER.contentJson(DishTestData.DISH_TODAY_RES_1_1));
    }

    @Test
    public void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RestaurantTestData.RESTAURANT_ID1 + "/" + DishTestData.DISH_TODAY_RES1_ID1))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    public void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RestaurantTestData.RESTAURANT_ID1 + "/" + DishTestData.NOT_FOUND)
                .with(userHttpBasic(UserTestData.admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RestaurantTestData.RESTAURANT_ID1)
                .with(userHttpBasic(UserTestData.admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DishTestData.MATCHER.contentJson(DishTestData.dishes));
    }

    @Test
    public void getFilter() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RestaurantTestData.RESTAURANT_ID1 + "/filter?date=" + DishTestData.TODAY.minusDays(1).format(DateTimeFormatter.ISO_DATE))
                .with(userHttpBasic(UserTestData.admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DishTestData.MATCHER.contentJson(DishTestData.DISH_YESTERDAY_RES_1_2, DishTestData.DISH_YESTERDAY_RES_1_1, DishTestData.DISH_YESTERDAY_RES_1_3));
    }

    @Test
    public void getToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RestaurantTestData.RESTAURANT_ID1 + "/today")
                .with(userHttpBasic(UserTestData.admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DishTestData.MATCHER.contentJson(DishTestData.DISH_TODAY_RES_1_2, DishTestData.DISH_TODAY_RES_1_1, DishTestData.DISH_TODAY_RES_1_3));
    }

    @Test
    public void createInvalid() throws Exception {
        Dish invalid = new Dish(null, "Invalid", RestaurantTestData.res1, 90, null);
        perform(MockMvcRequestBuilders.post(REST_URL + RestaurantTestData.RESTAURANT_ID1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(UserTestData.admin)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateInvalid() throws Exception {
        Dish invalid = new Dish(DISH_TODAY_RES1_ID1, null, RestaurantTestData.res1, 90, null);
        perform(MockMvcRequestBuilders.put(REST_URL + RestaurantTestData.RESTAURANT_ID1 + "/" + DISH_TODAY_RES1_ID1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(UserTestData.admin)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}