package ru.javaOps.votingLunch.model;

import java.time.LocalDateTime;

public class Vote extends AbstractBaseEntity {
    private User user;

    private Restaurant restaurant;

    private LocalDateTime dateTime;

    public Vote() {
    }

    public Vote(Integer id) {
        super(id);
    }

    public Vote(Integer id, User user, Restaurant restaurant, LocalDateTime dateTime) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
