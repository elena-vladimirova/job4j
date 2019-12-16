package ru.job4j.tracker;

import java.util.Random;

public enum TrackerSingle1 {

    INSTANCE;

    private final Item[] items = new Item[100];

    private int position = 0;

    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

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
}
