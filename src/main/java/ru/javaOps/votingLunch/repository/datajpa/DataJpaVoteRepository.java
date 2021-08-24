package ru.javaOps.votingLunch.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javaOps.votingLunch.model.Vote;
import ru.javaOps.votingLunch.repository.VoteRepository;

import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {
    @Override
    public Vote save(Vote vote, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Vote get(int id, int userId) {
        return null;
    }

    @Override
    public List<Vote> getAll(int userId) {
        return null;
    }
}
