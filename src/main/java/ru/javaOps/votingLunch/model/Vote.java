package ru.javaOps.votingLunch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import ru.javaOps.votingLunch.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {
    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @NotNull
    @Column(name = "date_time", nullable = false)
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
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

    public Vote(Vote v) {
        this(v.id, v.user, v.restaurant, v.dateTime);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Vote vote = (Vote) o;
        return Objects.equals(dateTime, vote.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateTime);
    }
}
