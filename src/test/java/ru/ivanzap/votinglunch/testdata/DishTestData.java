package ru.ivanzap.votinglunch.testdata;

import ru.ivanzap.votinglunch.MatcherFactory;
import ru.ivanzap.votinglunch.model.Dish;

import java.time.LocalDate;
import java.util.List;

import static ru.ivanzap.votinglunch.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant", "user");

    public static final LocalDate TODAY = LocalDate.now();

    public static final int DISH_YESTERDAY_RES1_ID1 = START_SEQ + 7;
    public static final int DISH_YESTERDAY_RES1_ID2 = START_SEQ + 8;
    public static final int DISH_YESTERDAY_RES1_ID3 = START_SEQ + 9;
    public static final int DISH_TODAY_RES1_ID1 = START_SEQ + 10;
    public static final int DISH_TODAY_RES1_ID2 = START_SEQ + 11;
    public static final int DISH_TODAY_RES1_ID3 = START_SEQ + 12;
    public static final int DISH_TODAY_RES2_ID1 = START_SEQ + 16;
    public static final int DISH_TODAY_RES2_ID2 = START_SEQ + 17;
    public static final int DISH_TODAY_RES2_ID3 = START_SEQ + 18;
    public static final int DISH_TODAY_RES3_ID1 = START_SEQ + 22;
    public static final int DISH_TODAY_RES3_ID2 = START_SEQ + 23;
    public static final int DISH_TODAY_RES3_ID3 = START_SEQ + 24;
    public static final int DISH_TODAY_RES4_ID1 = START_SEQ + 28;
    public static final int DISH_TODAY_RES4_ID2 = START_SEQ + 29;
    public static final int DISH_TODAY_RES4_ID3 = START_SEQ + 30;
    public static final int NOT_FOUND = 10;

    public static final Dish DISH_YESTERDAY_RES_1_1 =
            new Dish(DISH_YESTERDAY_RES1_ID1, "Sup Victoria 1", RestaurantTestData.res1, 70,
                    LocalDate.now().minusDays(1));
    public static final Dish DISH_YESTERDAY_RES_1_2 =
            new Dish(DISH_YESTERDAY_RES1_ID2, "Vtoroe Victoria 1", RestaurantTestData.res1, 150,
                    LocalDate.now().minusDays(1));
    public static final Dish DISH_YESTERDAY_RES_1_3 =
            new Dish(DISH_YESTERDAY_RES1_ID3, "Salat Victoria 1", RestaurantTestData.res1, 50,
                    LocalDate.now().minusDays(1));
    public static final Dish DISH_TODAY_RES_1_1 =
            new Dish(DISH_TODAY_RES1_ID1, "Sup Victoria 2", RestaurantTestData.res1, 50,
                    LocalDate.now());
    public static final Dish DISH_TODAY_RES_1_2 =
            new Dish(DISH_TODAY_RES1_ID2, "Vtoroe Victoria 2", RestaurantTestData.res1, 120,
                    LocalDate.now());
    public static final Dish DISH_TODAY_RES_1_3 =
            new Dish(DISH_TODAY_RES1_ID3, "Salat Victoria 2", RestaurantTestData.res1, 30,
                    LocalDate.now());

    public static final Dish DISH_RES_2_1 = new Dish(DISH_TODAY_RES2_ID1, "Sup White Eagle 2", RestaurantTestData.res2, 50, LocalDate.now());
    public static final Dish DISH_RES_2_2 = new Dish(DISH_TODAY_RES2_ID2, "Vtoroe White Eagle 2", RestaurantTestData.res2, 120, LocalDate.now());
    public static final Dish DISH_RES_2_3 = new Dish(DISH_TODAY_RES2_ID3, "Salat White Eagle 2", RestaurantTestData.res2, 30, LocalDate.now());

    public static final Dish DISH_RES_3_1 = new Dish(DISH_TODAY_RES3_ID1, "Sup Black and Peace 2", RestaurantTestData.res3, 50, LocalDate.now());
    public static final Dish DISH_RES_3_2 = new Dish(DISH_TODAY_RES3_ID2, "Vtoroe Black and Peace 2", RestaurantTestData.res3, 120, LocalDate.now());
    public static final Dish DISH_RES_3_3 = new Dish(DISH_TODAY_RES3_ID3, "Salat Black and Peace 2", RestaurantTestData.res3, 30, LocalDate.now());

    public static final Dish DISH_RES_4_1 = new Dish(DISH_TODAY_RES4_ID1, "Burger MCDonald's 2", RestaurantTestData.res4, 120, LocalDate.now());
    public static final Dish DISH_RES_4_2 = new Dish(DISH_TODAY_RES4_ID2, "Potato MCDonald's 2", RestaurantTestData.res4, 50, LocalDate.now());
    public static final Dish DISH_RES_4_3 = new Dish(DISH_TODAY_RES4_ID3, "Cola MCDonald's 2", RestaurantTestData.res4, 30, LocalDate.now());

    public static Dish getNew() {
        return new Dish(null, "newDish Victoria", RestaurantTestData.res1, 45,
                LocalDate.now());
    }

    public static List<Dish> dishes = List.of(DISH_YESTERDAY_RES_1_1, DISH_YESTERDAY_RES_1_2, DISH_YESTERDAY_RES_1_3,
            DISH_TODAY_RES_1_1, DISH_TODAY_RES_1_2, DISH_TODAY_RES_1_3);

    public static Dish getUpdated() {
        Dish updated = new Dish(DISH_TODAY_RES_1_1);
        updated.setName("updatedDish Victoria");
        updated.setPrice(78);
        return updated;
    }
}
