package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * Класс для работы с заявками.
 *
 * @author Elena Vladimirova (avtobusova@mail.ru)
 */
public class Tracker {

    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];
    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

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
        this.items[this.position++] = item;
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
        for (int i = 0; i < position; i++) {
            if (this.items[i].getId().equals(id)) {
                result = this.items[i];
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
    public Item[] findByName(String name) {
        Item[] result = new Item[position];
        int index = 0;
        for (int i = 0; i < position; i++) {
            if (this.items[i].getName().equals(name)) {
                result[index++] = this.items[i];
            }
        }
        return Arrays.copyOf(result, index);
    }

    /**
     * Метод возвращает список заявок без null элементов.
     *
     * @return Массив найденных заявок.
     */
    public Item[] findAll() {
        return Arrays.copyOf(items, position);
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
        for (int i = 0; i < position; i++) {
            if (this.items[i].getId().equals(id)) {
                this.items[i] = item;
                this.items[i].setId(id);
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
        for (int i = 0; i < position; i++) {
            if (id.equals(this.items[i].getId())) {
                System.arraycopy(this.items, i + 1, this.items, i, this.items.length - i - 1);
                this.items[this.items.length - 1] = null;
                result = true;
                position--;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Item i = tracker.findById("11");

    }

}