package ru.javaOps.votingLunch.model;

import java.util.Date;

public class User extends AbstractNameEntity {
    private String email;

    private Date registered;

    private Role role;

    public User(Integer id, String name) {
        super(id, name);
    }

    public User(Integer id, String name, String email, Date registered, Role role) {
        super(id, name);
        this.email = email;
        this.registered = registered;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
