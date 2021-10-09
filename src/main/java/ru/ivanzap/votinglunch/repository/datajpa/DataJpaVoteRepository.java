package ru.ivanzap.votinglunch.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanzap.votinglunch.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaVoteRepository {
    private final CrudVoteRepository crudVoteRepository;
    private final CrudUserRepository crudUserRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaVoteRepository(CrudVoteRepository crudVoteRepository, CrudUserRepository crudUserRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudVoteRepository = crudVoteRepository;
        this.crudUserRepository = crudUserRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Transactional
    public Vote save(Vote vote, int userId, int restaurantId) {
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            return null;
        }
        vote.setUser(crudUserRepository.getById(userId));
        vote.setRestaurant(crudRestaurantRepository.getById(restaurantId));
        return crudVoteRepository.save(vote);
    }

    public boolean delete(int id, int userId) {
        return crudVoteRepository.delete(id, userId) != 0;
    }

    public Vote get(int id, int userId) {
        return crudVoteRepository.findById(id)
                .filter(vote -> vote.getUser().getId() == userId)
                .orElse(null);
    }

    public Vote getToday(int userId) {
        return crudVoteRepository.getAll(userId).stream()
                .filter(v -> v.getDate().equals(LocalDate.now()))
                .findFirst().orElse(null);
    }

    public List<Vote> getAll(int userId) {
        return crudVoteRepository.getAll(userId);
    }
}
