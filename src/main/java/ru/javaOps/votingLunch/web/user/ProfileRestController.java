package ru.javaOps.votingLunch.web.user;

import org.springframework.stereotype.Controller;
import ru.javaOps.votingLunch.model.User;

@Controller
public class ProfileRestController extends AbstractUserController {
    @Override
    public void update(User user, int id) {
        super.update(user, id);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public User get(int id) {
        return super.get(id);
    }
}