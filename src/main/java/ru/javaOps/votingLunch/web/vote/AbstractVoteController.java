package ru.javaOps.votingLunch.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javaOps.votingLunch.model.Vote;
import ru.javaOps.votingLunch.service.VoteService;
import ru.javaOps.votingLunch.util.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javaOps.votingLunch.util.ValidationUtil.assureIdConsistent;
import static ru.javaOps.votingLunch.util.ValidationUtil.checkNew;

public abstract class AbstractVoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    public Vote create(Vote vote, int restaurantId) {
        int userId = SecurityUtil.authUserId();
        log.info("create {} by user {} restaurant {}", vote, userId, restaurantId);
        checkNew(vote);
        return service.create(vote, userId, restaurantId);
    }

    public void update(Vote vote, int id, int restaurantId) {
        int userId = SecurityUtil.authUserId();
        log.info("update {} by user {} restaurant {}", vote, userId, restaurantId);
        assureIdConsistent(vote, id);
        service.update(vote, userId, restaurantId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete {}", id);
        service.delete(id, userId);
    }

    public Vote get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get {}", id);
        return service.get(id, userId);
    }

    public List<Vote> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll");
        return service.getAll(userId);
    }

    public List<Vote> getAllVotesToday() {
        LocalDateTime toDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        log.info("getAllVotesToday");
        return service.getAllVotesToday(toDay);
    }
}
