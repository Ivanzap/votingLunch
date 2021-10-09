package ru.ivanzap.votinglunch.web.dish;

import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.ivanzap.votinglunch.model.Dish;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = ProfileDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileDishController extends AbstractDishController {
    static final String REST_URL = "/rest/dishes";

    @Override
    @GetMapping("/{restaurantId}/{id}")
    public Dish get(@PathVariable int id, @PathVariable int restaurantId) {
        return super.get(id, restaurantId);
    }

    @Override
    @GetMapping(value = "/{restaurantId}/filter")
    public List<Dish> getFilter(@RequestParam @Nullable LocalDate date, @PathVariable int restaurantId) {
        return super.getFilter(date, restaurantId);
    }

    @Override
    @GetMapping(value = "/{restaurantId}/today")
    public List<Dish> getToday(@PathVariable int restaurantId) {
        return super.getToday(restaurantId);
    }
}
