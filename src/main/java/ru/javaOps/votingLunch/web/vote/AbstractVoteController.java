package ru.javaOps.votingLunch.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javaOps.votingLunch.model.Vote;
import ru.javaOps.votingLunch.service.VoteService;

import java.util.List;

import static ru.javaOps.votingLunch.util.ValidationUtil.assureIdConsistent;
import static ru.javaOps.votingLunch.util.ValidationUtil.checkNew;

@Controller
public abstract class AbstractVoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    public Vote create(Vote vote, int userId) {
        log.info("create {} with id={}", vote, userId);
        checkNew(vote);
        return service.create(vote, userId);
    }

    public void update(Vote vote, int userId) {
        log.info("update {} with id={}", vote, userId);
        assureIdConsistent(vote, userId);
        service.update(vote, userId);
    }

    public void delete(int id, int userId) {
        log.info("delete {}", id);
        service.delete(id, userId);
    }

    public Vote get(int id, int userId) {
        log.info("get {}", id);
        return service.get(id, userId);
    }

    public List<Vote> getAll(int userId) {
        log.info("getAll");
        return service.getAll(userId);
    }
}
