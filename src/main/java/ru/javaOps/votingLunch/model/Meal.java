package ru.javaOps.votingLunch.model;

import java.time.LocalDateTime;

public class Meal extends AbstractNameEntity {
    private Restaurant restaurant;

    private double price;

    private LocalDateTime dateTime;

    public Meal(Integer id, String name) {
        super(id, name);
    }
}
