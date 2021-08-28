package ru.javaOps.votingLunch.model;

import java.time.LocalDateTime;

public class Meal extends AbstractNameEntity {
    private Restaurant restaurant;

    private double price;

    private LocalDateTime dateTime;

    public Meal() {
    }

    public Meal(Integer id, String name) {
        super(id, name);
    }

    public Meal(Integer id, String name, Restaurant restaurant, double price, LocalDateTime dateTime) {
        super(id, name);
        this.restaurant = restaurant;
        this.price = price;
        this.dateTime = dateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
