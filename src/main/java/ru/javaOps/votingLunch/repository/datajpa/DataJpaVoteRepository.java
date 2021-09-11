package ru.javaOps.votingLunch.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javaOps.votingLunch.model.Vote;
import ru.javaOps.votingLunch.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {
    private final CrudVoteRepository crudVoteRepository;
    private final CrudUserRepository crudUserRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaVoteRepository(CrudVoteRepository crudVoteRepository, CrudUserRepository crudUserRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudVoteRepository = crudVoteRepository;
        this.crudUserRepository = crudUserRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Vote save(Vote vote, int userId, int restaurantId) {
        if (!vote.isNew() && get(vote.getId(), userId) == null
                || !vote.getDateTime().toLocalDate().equals(LocalDate.now())) {
            return null;
        }
        if (vote.getDateTime().toLocalTime().isAfter(LocalTime.of(11, 0, 0))) {
            return null;
        }
        vote.setUser(crudUserRepository.getById(userId));
        vote.setRestaurant(crudRestaurantRepository.getById(restaurantId));
        return crudVoteRepository.save(vote);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudVoteRepository.delete(id, userId) != 0;
    }

    @Override
    public Vote get(int id, int userId) {
        return crudVoteRepository.findById(id)
                .filter(vote -> vote.getUser().getId() == userId && vote.getDateTime().toLocalDate().equals(LocalDate.now()))
                .orElse(null);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return crudVoteRepository.getAll(userId);
    }

    @Override
    public List<Vote> getAllVotesToday(LocalDateTime toDay) {
        return crudVoteRepository.getAllVotesToday(toDay);
    }
}
