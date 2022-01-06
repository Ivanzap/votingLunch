package ru.ivanzap.votinglunch.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.ivanzap.votinglunch.model.Vote;
import ru.ivanzap.votinglunch.repository.datajpa.DataJpaVoteRepository;

import java.time.LocalTime;
import java.util.List;

import static ru.ivanzap.votinglunch.util.validation.ValidationUtil.checkNotFound;
import static ru.ivanzap.votinglunch.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    public static final LocalTime DEADLINE = LocalTime.of(11, 0);

    private final DataJpaVoteRepository repository;

    public VoteService(DataJpaVoteRepository repository) {
        this.repository = repository;
    }

    public Vote create(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote, userId, restaurantId);
    }

    public void update(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        Assert.isTrue(LocalTime.now().isBefore(DEADLINE), "It's too late to update you vote");
        checkNotFoundWithId(repository.save(vote, userId, restaurantId), vote.getId());
    }

    public void delete(int id, int userId) {
        Vote vote = repository.get(id, userId);
        checkNotFoundWithId(vote, id);
        Assert.isTrue(LocalTime.now().isBefore(DEADLINE), "It's too late to delete you vote");
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public Vote get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public Vote getToday(int userId) {
        return checkNotFound(repository.getToday(userId), "Not found vote today");
    }

    public List<Vote> getAll(int userId) {
        return repository.getAll(userId);
    }
}
