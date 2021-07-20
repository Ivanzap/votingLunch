package ru.javaOps.votingLunch.model;

import java.time.LocalDateTime;

public class User extends AbstractNameEntity {
    private String Email;

    private LocalDateTime registered;

    private Role role;

    public User(Integer id, String name) {
        super(id, name);
    }
}
