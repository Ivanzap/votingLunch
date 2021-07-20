package ru.javaOps.votingLunch.model;

import java.time.LocalDateTime;

public class Vote extends AbstractBaseEntity {
    private User user;

    private Restaurant restaurant;

    private LocalDateTime dateTime;

    public Vote(Integer id) {
        super(id);
    }
}
