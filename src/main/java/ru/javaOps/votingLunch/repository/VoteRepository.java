package ru.javaOps.votingLunch.repository;

import ru.javaOps.votingLunch.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote, int userId, int restaurantId);

    boolean delete(int id, int userId);

    Vote get(int id, int userId);

    List<Vote> getAll(int userId);

    List<Vote> getAllVotesToday(LocalDateTime toDay);
}
