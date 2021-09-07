package ru.javaOps.votingLunch;

import ru.javaOps.votingLunch.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javaOps.votingLunch.RestaurantTestData.res1;
import static ru.javaOps.votingLunch.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final MatcherFactory<Meal> MATCHER = MatcherFactory.usingIgnoringFieldsComparator("restaurant", "user");

    public static final LocalDateTime TODAY = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);

    public static final int MEAL_YESTERDAY_RES1_ID1 = START_SEQ + 7;
    public static final int MEAL_YESTERDAY_RES1_ID2 = START_SEQ + 8;
    public static final int MEAL_YESTERDAY_RES1_ID3 = START_SEQ + 9;
    public static final int MEAL_TODAY_RES1_ID1 = START_SEQ + 10;
    public static final int MEAL_TODAY_RES1_ID2 = START_SEQ + 11;
    public static final int MEAL_TODAY_RES1_ID3 = START_SEQ + 12;
    public static final int MEAL_TODAY_RES2_ID1 = START_SEQ + 16;
    public static final int MEAL_TODAY_RES2_ID2 = START_SEQ + 17;
    public static final int MEAL_TODAY_RES2_ID3 = START_SEQ + 18;
    public static final int MEAL_TODAY_RES3_ID1 = START_SEQ + 22;
    public static final int MEAL_TODAY_RES3_ID2 = START_SEQ + 23;
    public static final int MEAL_TODAY_RES3_ID3 = START_SEQ + 24;
    public static final int MEAL_TODAY_RES4_ID1 = START_SEQ + 28;
    public static final int MEAL_TODAY_RES4_ID2 = START_SEQ + 29;
    public static final int MEAL_TODAY_RES4_ID3 = START_SEQ + 30;
    public static final int NOT_FOUND = 10;

    public static final Meal mealYesterdayRes1_1 =
            new Meal(MEAL_YESTERDAY_RES1_ID1, "Sup Victoria 1", res1, 70,
                    LocalDate.now().minusDays(1).atTime(7, 10));
    public static final Meal mealYesterdayRes1_2 =
            new Meal(MEAL_YESTERDAY_RES1_ID2, "Vtoroe Victoria 1", res1, 150,
                    LocalDate.now().minusDays(1).atTime(7, 11));
    public static final Meal mealYesterdayRes1_3 =
            new Meal(MEAL_YESTERDAY_RES1_ID3, "Salat Victoria 1", res1, 50,
                    LocalDate.now().minusDays(1).atTime(7, 12));
    public static final Meal mealTodayRes1_1 =
            new Meal(MEAL_TODAY_RES1_ID1, "Sup Victoria 2", res1, 50,
                    LocalDate.now().atTime(7, 10));
    public static final Meal mealTodayRes1_2 =
            new Meal(MEAL_TODAY_RES1_ID2, "Vtoroe Victoria 2", res1, 120,
                    LocalDate.now().atTime(7, 11));
    public static final Meal mealTodayRes1_3 =
            new Meal(MEAL_TODAY_RES1_ID3, "Salat Victoria 2", res1, 30,
                    LocalDate.now().atTime(7, 12));

    public static Meal getNew() {
        return new Meal(null, "newMeal Victoria", res1, 45,
                LocalDate.now().atTime(9, 20));
    }

    public static List<Meal> meals = List.of(mealYesterdayRes1_1, mealYesterdayRes1_2, mealYesterdayRes1_3,
            mealTodayRes1_1, mealTodayRes1_2, mealTodayRes1_3);

    public static Meal getUpdated() {
        Meal updated = new Meal(mealTodayRes1_1);
        updated.setName("updatedMeal Victoria");
        updated.setPrice(78);
        updated.setDateTime(mealTodayRes1_1.getDateTime().plusMinutes(20));
        return updated;
    }
}
