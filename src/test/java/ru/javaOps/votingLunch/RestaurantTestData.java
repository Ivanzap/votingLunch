package ru.javaOps.votingLunch;

import ru.javaOps.votingLunch.model.Restaurant;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javaOps.votingLunch.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT_ID1 = START_SEQ + 3;
    public static final int RESTAURANT_ID2 = START_SEQ + 4;
    public static final int RESTAURANT_ID3 = START_SEQ + 5;
    public static final int RESTAURANT_ID4 = START_SEQ + 6;

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

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("user", "registered").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user", "registered").isEqualTo(expected);
    }
}
