package ru.javaOps.votingLunch.model;

import java.time.LocalDateTime;

public class Restaurant extends AbstractNameEntity {
    private LocalDateTime registered;

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}
