package ru.job4j.generics;

public class User extends Base {

    private String userName;

    public User(String id, String userName) {
        super(id);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
