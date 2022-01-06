package ru.ivanzap.votinglunch.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ru.ivanzap.votinglunch.View;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"date_dish", "restaurant_id", "name"}, name = "dishes_unique_date_restaurant_name_idx")})
public class Dish extends AbstractNameEntity {

    @JsonBackReference("Restaurant_dish_reference")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(groups = View.Persist.class)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @JoinColumn(name = "price", nullable = false)
    private int price;

    @NotNull
    @Column(name = "date_dish", nullable = false)
    private LocalDate date;

    public Dish() {
    }

    public Dish(Integer id, String name, Restaurant restaurant, int price, LocalDate date) {
        super(id, name);
        this.restaurant = restaurant;
        this.price = price;
        this.date = date;
    }

    public Dish(Dish m) {
        this(m.id, m.name, m.restaurant, m.price, m.date);
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

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
