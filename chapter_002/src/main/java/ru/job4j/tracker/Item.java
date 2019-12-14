package ru.job4j.tracker;

import java.util.Arrays;
import java.util.List;

/**
 * Класс, описывающий заявки.
 *
 * @author Elena Vladimirova (avtobusova@mail.ru)
 */
public class Item implements Comparable<Item>{
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
}