package ru.ivanzap.votinglunch.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.ivanzap.votinglunch.View;
import ru.ivanzap.votinglunch.model.Dish;
import ru.ivanzap.votinglunch.service.DishService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.ivanzap.votinglunch.util.validation.ValidationUtil.assureIdConsistent;
import static ru.ivanzap.votinglunch.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishController {
    static final String REST_URL = "/rest/admin/dishes";
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Validated(View.Web.class) @RequestBody Dish dish, @PathVariable int restaurantId) {
        log.info("create {}", dish);
        checkNew(dish);
        Dish created = service.create(dish, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restaurantId}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Dish dish, @PathVariable int restaurantId) {
        log.info("update {}", dish);
        assureIdConsistent(dish, dish.getId());
        service.update(dish, restaurantId);
    }

    @DeleteMapping("/{restaurantId}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("delete {}", id);
        service.delete(id, restaurantId);
    }

    @GetMapping("/{restaurantId}/{id}")
    public Dish get(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("get {}", id);
        return service.get(id, restaurantId);
    }

    @GetMapping("/{restaurantId}")
    public List<Dish> getAll(@PathVariable int restaurantId) {
        log.info("getAll");
        return service.getAll(restaurantId);
    }

    @GetMapping(value = "/{restaurantId}/filter")
    public List<Dish> getFilter(@RequestParam @Nullable LocalDate date, @PathVariable int restaurantId) {
        log.info("getFilter {}", restaurantId);
        return service.getFilter(restaurantId, date);
    }

    @GetMapping(value = "/{restaurantId}/today")
    public List<Dish> getToday(@PathVariable int restaurantId) {
        log.info("getToday {}", restaurantId);
        return service.getToday(restaurantId);
    }
}
