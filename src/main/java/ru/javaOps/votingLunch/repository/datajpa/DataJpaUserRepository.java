package ru.javaOps.votingLunch.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javaOps.votingLunch.model.User;
import ru.javaOps.votingLunch.repository.UserRepository;

import java.util.List;

@Repository
public class DataJpaUserRepository implements UserRepository {

    private final CrudUserRepository crudRepository;

    public DataJpaUserRepository(CrudUserRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public User save(User user) {
        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
