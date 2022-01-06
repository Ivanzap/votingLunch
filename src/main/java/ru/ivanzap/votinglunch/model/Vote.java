package ru.ivanzap.votinglunch.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.ivanzap.votinglunch.View;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_vote"}, name = "votes_unique_user_date_idx")})
public class Vote extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(groups = View.Persist.class)
    @JsonBackReference("User_vote_reference")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(groups = View.Persist.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("Restaurant_vote_reference")
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "date_vote", nullable = false)
    private LocalDate date;

    @Column(name = "time_vote", nullable = false)
    private LocalTime time;

    public Vote() {
    }

    public Vote(Integer id, User user, Restaurant restaurant, LocalDate date, LocalTime time) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.date = date;
        this.time = time;
    }

    public Vote(Vote v) {
        this(v.id, v.user, v.restaurant, v.date, v.time);
    }

    @PrePersist
    public void created() {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    @PreUpdate
    public void updated() {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
