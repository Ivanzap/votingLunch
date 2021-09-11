package ru.javaOps.votingLunch;

import ru.javaOps.votingLunch.model.Restaurant;

import static ru.javaOps.votingLunch.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "registered", "user");

    public static final int RESTAURANT_ID1 = START_SEQ + 3;
    public static final int RESTAURANT_ID2 = START_SEQ + 4;
    public static final int RESTAURANT_ID3 = START_SEQ + 5;
    public static final int RESTAURANT_ID4 = START_SEQ + 6;
    public static final int NOT_FOUND = 10;

    public static final Restaurant res1 = new Restaurant(RESTAURANT_ID1, "Victoria");
    public static final Restaurant res2 = new Restaurant(RESTAURANT_ID2, "White Eagle");
    public static final Restaurant res3 = new Restaurant(RESTAURANT_ID3, "Black and Peace");
    public static final Restaurant res4 = new Restaurant(RESTAURANT_ID4, "MCDonald's");

    public static Restaurant getNew() {
        return new Restaurant(null, "NewRestaurant");
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(res1);
        updated.setName("UpdateRestaurant");
        return updated;
    }
}
