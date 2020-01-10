package ru.job4j.generics;

public class RoleStore extends AbstractStore<Role> {

    public RoleStore(int size) {
        super(new SimpleArray<>(size));
    }

}