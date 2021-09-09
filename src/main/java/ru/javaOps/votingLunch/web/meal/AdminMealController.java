package ru.javaOps.votingLunch.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaOps.votingLunch.model.Meal;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminMealController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminMealController extends AbstractMealController {
    static final String REST_URL = "/rest/admin/meals";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal) {
        Meal created = super.create(meal);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal, @PathVariable int id) {
        super.update(meal, id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<Meal> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/restaurant/all_menu/{restaurantId}")
    public List<Meal> getAllMenuOfRestaurant(@PathVariable int restaurantId) {
        return super.getAllMenuOfRestaurant(restaurantId);
    }

    @Override
    @GetMapping("/restaurant/today_menu/{restaurantId}")
    public List<Meal> getMenuTodayOfRestaurant(@PathVariable int restaurantId) {
        return super.getMenuTodayOfRestaurant(restaurantId);
    }
}