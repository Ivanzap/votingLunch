package ru.javaOps.votingLunch.model;

import java.time.LocalDateTime;

public class Restaurant extends AbstractNameEntity {
    private LocalDateTime registered;

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(Integer id, String name, LocalDateTime registered) {
        super(id, name);
        this.registered = registered;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }
}
