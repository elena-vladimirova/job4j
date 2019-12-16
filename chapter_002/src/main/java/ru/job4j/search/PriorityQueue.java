package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод для добавления элемента в указанную позицию.
     * Позиция определяется по полю приоритет.
     * Для вставик использовать add(int index, E value)
     *
     * @param task задача
     */
    public void put(Task task) {

        int idx = 0;
        if (this.tasks.size() != 0) {
            for (Task t : this.tasks) {
                if (t.getPriority() >= task.getPriority()) {
                    break;
                } else {
                    idx++;
                }
            }
        }
        this.tasks.add(idx, task);
    }

    public Task take() {
        return this.tasks.poll();
    }

    public static void main(String[] args) {

        PriorityQueue q = new PriorityQueue();
        q.put(new Task("Приготовить обед", 4));
        q.put(new Task("Сделать с Таней уроки", 3));
        q.put(new Task("Отвезти Диму на прививку", 3));
        q.put(new Task("Сделать эту задачу", 1));

        System.out.println(q.take());
        System.out.println(q.take());
        System.out.println(q.take());
        System.out.println(q.take());
    }
}