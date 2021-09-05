package ru.javaOps.votingLunch.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javaOps.votingLunch.model.Vote;
import ru.javaOps.votingLunch.repository.VoteRepository;

import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {
    private final CrudVoteRepository crudVoteRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaVoteRepository(CrudVoteRepository crudVoteRepository, CrudUserRepository crudUserRepository) {
        this.crudVoteRepository = crudVoteRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Vote save(Vote vote, int userId) {
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            return null;
        }
        vote.setUser(crudUserRepository.getById(userId));
        return crudVoteRepository.save(vote);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudVoteRepository.delete(id, userId) != 0;
    }

    @Override
    public Vote get(int id, int userId) {
        return crudVoteRepository.findById(id)
                .filter(vote -> vote.getUser().getId() == userId)
                .orElse(null);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return crudVoteRepository.getAll(userId);
    }
}
