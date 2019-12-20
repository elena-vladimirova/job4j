package ru.job4j.collection.transfers;

import java.util.Objects;

/**
 * Класс для работы с пользователями.
 *
 * @author Elena Vladimirova (avtobusova@mail.ru)
 */
public class User {

    /**
     * Уникальный ключ: данные о паспорте
     */
    String passport;
    /**
     * Имя пользователя
     */
    String name;

    public User(String passport, String name) {
        this.passport = passport;
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return passport.equals(user.passport) && name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport, name);
    }
}
