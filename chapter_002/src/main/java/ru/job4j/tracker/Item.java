package ru.job4j.tracker;

import java.util.Objects;

/**
 * Класс, описывающий заявки.
 *
 * @author Elena Vladimirova (avtobusova@mail.ru)
 */
public class Item implements Comparable<Item> {
    /**
     * Идентификатор заявки.
     */
    private String id;
    /**
     * Описание заявки.
     */
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.id + " : " + this.name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Item another) {
        return this.getName().compareTo(another.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}