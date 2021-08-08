package ru.javaOps.votingLunch.repository.datajpa;

import ru.javaOps.votingLunch.model.User;
import ru.javaOps.votingLunch.repository.UserRepository;

import java.util.List;

public class DataJpaUserRepository implements UserRepository {
    @Override
    public User save(User user) {
        return null;
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
