package ru.javaOps.votingLunch.to;

import ru.javaOps.votingLunch.model.Restaurant;
import ru.javaOps.votingLunch.model.Role;
import ru.javaOps.votingLunch.model.Vote;

import java.util.Date;

public class UserVoteTo {
    private Integer id;

    private String name;

    private String email;

    private Date registered;

    private Role role;

    private Restaurant restaurant;

    private Vote vote;
}
