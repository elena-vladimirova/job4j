package ru.job4j.generics;

public class UserStore extends AbstractStore {

    public UserStore(int size) {
        super(new SimpleArray<User>(size));
    }

}
