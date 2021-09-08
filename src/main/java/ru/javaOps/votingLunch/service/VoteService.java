package ru.javaOps.votingLunch.service;

import org.springframework.stereotype.Service;
import ru.javaOps.votingLunch.model.Vote;
import ru.javaOps.votingLunch.repository.VoteRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javaOps.votingLunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final VoteRepository repository;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote create(Vote vote, int userId, int restaurantId) {
        return repository.save(vote, userId, restaurantId);
    }

    public void update(Vote vote, int userId, int restaurantId) {
        checkNotFoundWithId(repository.save(vote, userId, restaurantId), vote.getId());
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public Vote get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public List<Vote> getAll(int userId) {
        return repository.getAll(userId);
    }

    public List<Vote> getAllVotesToday(LocalDateTime toDay) {
        return repository.getAllVotesToday(toDay);
    }
}
