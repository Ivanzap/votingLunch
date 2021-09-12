package ru.javaOps.votingLunch.web.meal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaOps.votingLunch.model.Meal;

import java.util.List;

@RestController
@RequestMapping(value = ProfileMealController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileMealController extends AbstractMealController {
    static final String REST_URL = "/rest/restaurants/meals";

    @Override
    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping("/today_menu/{restaurantId}")
    public List<Meal> getMenuTodayOfRestaurant(@PathVariable int restaurantId) {
        return super.getMenuTodayOfRestaurant(restaurantId);
    }
}
