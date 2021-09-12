package ru.javaOps.votingLunch.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import ru.javaOps.votingLunch.AuthorizedUser;
import ru.javaOps.votingLunch.model.User;
import ru.javaOps.votingLunch.service.UserService;
import ru.javaOps.votingLunch.to.UserTo;
import ru.javaOps.votingLunch.util.UserUtil;

import java.util.List;

import static ru.javaOps.votingLunch.util.ValidationUtil.assureIdConsistent;
import static ru.javaOps.votingLunch.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    @Autowired
    private UniqueMailValidator emailValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(emailValidator);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public User create(UserTo userTo) {
        log.info("create {}", userTo);
        checkNew(userTo);
        return service.create(UserUtil.createNewFromTo(userTo));
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        assureIdConsistent(userTo, id);
        service.update(userTo);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }
}
