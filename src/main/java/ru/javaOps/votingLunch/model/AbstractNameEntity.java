package ru.javaOps.votingLunch.model;

public abstract class AbstractNameEntity extends AbstractBaseEntity {
    protected String name;

    public AbstractNameEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
