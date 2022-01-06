package ru.ivanzap.votinglunch;

import ru.ivanzap.votinglunch.model.User;
import ru.ivanzap.votinglunch.to.UserTo;
import ru.ivanzap.votinglunch.util.UserUtil;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private UserTo userTo;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        setTo(UserUtil.asTo(user));
    }

    public int getId() {
        return userTo.getId();
    }

    public void setTo(UserTo newTo) {
        newTo.setPassword(null);
        userTo = newTo;
    }

    public UserTo getUserTo() {
        return userTo;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}
