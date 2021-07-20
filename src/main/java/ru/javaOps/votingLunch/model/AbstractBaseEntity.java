package ru.javaOps.votingLunch.model;

public abstract class AbstractBaseEntity {
    protected Integer id;

    public AbstractBaseEntity(Integer id) {
        this.id = id;
    }
}
