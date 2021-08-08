package ru.javaOps.votingLunch.model;

import java.util.Date;

public class Restaurant extends AbstractNameEntity {
    private Date registered;

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(Integer id, String name, Date registered) {
        super(id, name);
        this.registered = registered;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }
}
