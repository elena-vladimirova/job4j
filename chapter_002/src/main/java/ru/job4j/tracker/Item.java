package ru.job4j.tracker;

/**
 * Класс, описывающий заявки.
 *
 * @author Elena Vladimirova (avtobusova@mail.ru)
 */
public class Item {
    /** Идентификатор заявки. */
    private String id;
    /** Описание заявки. */
    private String name;

    public Item(String name) {
        this.name = name;
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
}