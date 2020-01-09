package ru.job4j.generics;

public class RoleStore extends AbstractStore {

    public RoleStore(int size) {
        super(new SimpleArray<Role>(size));
    }

}