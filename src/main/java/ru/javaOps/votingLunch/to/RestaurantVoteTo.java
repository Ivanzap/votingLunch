package ru.javaOps.votingLunch.to;

import java.time.LocalDateTime;

public class RestaurantVoteTo {
    private Integer id;

    private String name;

    private LocalDateTime dateTime;

    private boolean excess;

    public RestaurantVoteTo(Integer id, String name, LocalDateTime dateTime, boolean excess) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
        this.excess = excess;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public boolean isExcess() {
        return excess;
    }
}
