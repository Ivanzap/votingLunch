package ru.javaOps.votingLunch.service;

import ru.javaOps.votingLunch.model.User;
import ru.javaOps.votingLunch.repository.UserRepository;

import java.util.List;

import static ru.javaOps.votingLunch.util.ValidationUtil.checkNotFound;
import static ru.javaOps.votingLunch.util.ValidationUtil.checkNotFoundWithId;


public class UserService {

    private UserRepository repository;

    public User create(User user) {
        return repository.save(user);
    }

    public void update(User user) {
        checkNotFoundWithId(repository.save(user), user.getId());
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(String email) {
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return repository.getAll();
    }
}
