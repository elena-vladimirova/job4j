package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс для работы с заявками.
 *
 * @author Elena Vladimirova (avtobusova@mail.ru)
 */
public class Tracker implements ITracker {

    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList();
    /**
     * Указатель ячейки для новой заявки.
     */
    //private int position = 0;

    /**
     * Метод генерирует уникальный ключ для заявки.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    /**
     * Метод, реализаущий добавление заявки в хранилище.
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод возвращает заявку по Id.
     *
     * @param id идентификатор заявки
     * @return Найденная заявка.
     */
    public Item findById(String id) {
        Item result = null;
        for (int i = 0; i < items.size(); i++) {
            if (this.items.get(i).getId().equals(id)) {
                result = this.items.get(i);
                break;
            }
        }
        return result;
    }

    /**
     * Метод возвращает заявки по name.
     *
     * @param name описание заявки
     * @return Массив найденных заявок.
     */
    public List<Item> findByName(String name) {
        List<Item> result = new ArrayList();
        for (int i = 0; i < items.size(); i++) {
            if (this.items.get(i).getName().equals(name)) {
                result.add(this.items.get(i));
            }
        }
        return result;
    }

    /**
     * Метод возвращает список заявок без null элементов.
     *
     * @return Массив найденных заявок.
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Метод заменяет ячейку в массиве.
     *
     * @param id   id заменяемой заявки
     * @param item новая заявка
     * @return true, если заявка заменена, иначе - false
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId().equals(id)) {
                this.items.set(i, item).setId(id);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод удаляет заявку по id со смещением массива заявок влево.
     *
     * @param id id заявки
     * @return true, если заявка удалена, иначе - false
     */
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < this.items.size(); i++) {
            if (id.equals(this.items.get(i).getId())) {
                this.items.remove(i);
                result = true;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ITracker tracker = new Tracker();
        Item i = tracker.findById("11");

    }

}