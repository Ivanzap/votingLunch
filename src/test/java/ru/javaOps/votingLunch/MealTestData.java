package ru.javaOps.votingLunch;

import ru.javaOps.votingLunch.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javaOps.votingLunch.RestaurantTestData.res1;
import static ru.javaOps.votingLunch.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final MatcherFactory<Meal> MATCHER = MatcherFactory.usingIgnoringFieldsComparator("restaurant", "user");

    public static final int MEAL_RES1_ID1 = START_SEQ + 10;
    public static final int MEAL_RES1_ID2 = START_SEQ + 11;
    public static final int MEAL_RES1_ID3 = START_SEQ + 12;
    public static final int MEAL_RES2_ID1 = START_SEQ + 16;
    public static final int MEAL_RES2_ID2 = START_SEQ + 17;
    public static final int MEAL_RES2_ID3 = START_SEQ + 18;
    public static final int MEAL_RES3_ID1 = START_SEQ + 22;
    public static final int MEAL_RES3_ID2 = START_SEQ + 23;
    public static final int MEAL_RES3_ID3 = START_SEQ + 24;
    public static final int MEAL_RES4_ID1 = START_SEQ + 28;
    public static final int MEAL_RES4_ID2 = START_SEQ + 29;
    public static final int MEAL_RES4_ID3 = START_SEQ + 30;

    public static final Meal mealRes1_1 =
            new Meal(MEAL_RES1_ID1, "Sup Victoria 2", res1, 50,
                    LocalDateTime.of(2021, 7, 2, 9, 20));

    public static Meal getNew() {
        return new Meal(null, "newMeal Victoria", res1, 45,
                LocalDateTime.of(2021, 7, 2, 9, 40));
    }

    List<Meal> meals;

    public static Meal getUpdated() {
        Meal updated = new Meal(mealRes1_1);
        updated.setName("updatedMeal Victoria");
        updated.setPrice(78);
        updated.setDateTime(mealRes1_1.getDateTime().plusMinutes(20));
        return updated;
    }
}
