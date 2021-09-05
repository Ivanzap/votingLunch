package ru.javaOps.votingLunch.model;

import org.springframework.format.annotation.DateTimeFormat;
import ru.javaOps.votingLunch.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "meals")
public class Meal extends AbstractNameEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @NotNull
    @JoinColumn(name = "price", nullable = false)
    private double price;

    @NotNull
    @Column(name = "date_time", nullable = false)
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
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

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }
}
