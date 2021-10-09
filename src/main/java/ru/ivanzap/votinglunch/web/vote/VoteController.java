package ru.ivanzap.votinglunch.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.ivanzap.votinglunch.AuthorizedUser;
import ru.ivanzap.votinglunch.View;
import ru.ivanzap.votinglunch.model.Vote;
import ru.ivanzap.votinglunch.service.VoteService;

import java.net.URI;
import java.util.List;

import static ru.ivanzap.votinglunch.util.validation.ValidationUtil.assureIdConsistent;
import static ru.ivanzap.votinglunch.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public static final String REST_URL = "/rest/votes";

    @Autowired
    private VoteService service;

    @PostMapping(value = "/restaurants/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@Validated(View.Web.class) @RequestBody Vote vote, @PathVariable int restaurantId, @AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("create {} by user {} restaurant {}", vote, authUser.getId(), restaurantId);
        checkNew(vote);
        Vote created = service.create(vote, authUser.getId(), restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated(View.Web.class) @RequestBody Vote vote, @PathVariable int restaurantId, @PathVariable int id, @AuthenticationPrincipal AuthorizedUser authUser) {
        int userId = authUser.getId();
        log.info("update {} by user {} restaurant {}", vote, userId, restaurantId);
        assureIdConsistent(vote, id);
        service.update(vote, userId, restaurantId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("delete {}", id);
        service.delete(id, authUser.getId());
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id, @AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("get {}", id);
        return service.get(id, authUser.getId());
    }

    @GetMapping("/today")
    public Vote getToday(@AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("getToday");
        return service.getToday(authUser.getId());
    }

    @GetMapping
    public List<Vote> getAll(@AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("getAll");
        return service.getAll(authUser.getId());
    }
}
