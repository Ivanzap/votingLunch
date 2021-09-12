package ru.javaOps.votingLunch.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javaOps.votingLunch.AuthorizedUser;
import ru.javaOps.votingLunch.model.User;
import ru.javaOps.votingLunch.repository.UserRepository;
import ru.javaOps.votingLunch.to.UserTo;
import ru.javaOps.votingLunch.util.UserUtil;

import java.util.List;

import static ru.javaOps.votingLunch.util.UserUtil.prepareToSave;
import static ru.javaOps.votingLunch.util.ValidationUtil.checkNotFound;
import static ru.javaOps.votingLunch.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(User user) {
        Assert.notNull(user, "user must be not null");
        return prepareAndSave(user);
    }

    public void update(User user) {
        checkNotFoundWithId(repository.save(user), user.getId());
        Assert.notNull(user, "user must not be null");
        prepareAndSave(user);
    }

    public void update(UserTo userTo) {
        Assert.notNull(userTo, "user must not be null");
        User user = get(userTo.id());
        prepareAndSave(UserUtil.updateFromTo(user, userTo));
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

    public User getWithVotes(int id) {
        return checkNotFoundWithId(repository.getWithVotes(id), id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }

    private User prepareAndSave(User user) {
        return repository.save(prepareToSave(user, passwordEncoder));
    }
}
