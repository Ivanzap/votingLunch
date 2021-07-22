package ru.javaOps.votingLunch.model;

import java.util.Date;

public class User extends AbstractNameEntity {
    private String email;

    private String password;

    private Date registered;

    private boolean enabled = true;

    private Role role;

    public User(Integer id, String name) {
        super(id, name);
    }

    public User(Integer id, String name, String email, String password, Date registered, boolean enabled, Role role) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.enabled = enabled;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
