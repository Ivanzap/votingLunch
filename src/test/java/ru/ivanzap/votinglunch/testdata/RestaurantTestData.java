package ru.ivanzap.votinglunch.testdata;

import ru.ivanzap.votinglunch.MatcherFactory;
import ru.ivanzap.votinglunch.model.Restaurant;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.ivanzap.votinglunch.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menu", "votes");
    public static MatcherFactory.Matcher<Restaurant> WITH_DISHES_MATCHER =
            MatcherFactory.usingAssertions(Restaurant.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("votes", "dish.restaurant").isEqualTo(e),
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("votes", "dish.restaurant").isEqualTo(e));

    public static final int RESTAURANT_ID1 = START_SEQ + 3;
    public static final int RESTAURANT_ID2 = START_SEQ + 4;
    public static final int RESTAURANT_ID3 = START_SEQ + 5;
    public static final int RESTAURANT_ID4 = START_SEQ + 6;
    public static final int NOT_FOUND = 10;

    public static final Restaurant res1 = new Restaurant(RESTAURANT_ID1, "Victoria");
    public static final Restaurant res2 = new Restaurant(RESTAURANT_ID2, "White Eagle");
    public static final Restaurant res3 = new Restaurant(RESTAURANT_ID3, "Black and Peace");
    public static final Restaurant res4 = new Restaurant(RESTAURANT_ID4, "MCDonald's");

    static {
        res1.setMenu(List.of(DishTestData.DISH_TODAY_RES_1_3, DishTestData.DISH_TODAY_RES_1_1, DishTestData.DISH_TODAY_RES_1_2));
        res2.setMenu(List.of(DishTestData.DISH_RES_2_3, DishTestData.DISH_RES_2_1, DishTestData.DISH_RES_2_2));
        res3.setMenu(List.of(DishTestData.DISH_RES_3_3, DishTestData.DISH_RES_3_1, DishTestData.DISH_RES_3_2));
        res4.setMenu(List.of(DishTestData.DISH_RES_4_1, DishTestData.DISH_RES_4_3, DishTestData.DISH_RES_4_2));
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "NewRestaurant");
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(res1);
        updated.setName("UpdateRestaurant");
        return updated;
    }
}
