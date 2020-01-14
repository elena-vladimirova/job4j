package ru.job4j.generics;

class Node<T> {

    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    /**
     * Метод определяет, является ли связанный список цикличным.
     * Используется алгоритм "Черепаха и заяц"
     * @param first - первый элемент списка
     * @return true, если список цикличный
     */
    public static boolean hasCycle(Node first) {
        boolean result = false;
        Node turtle = first;
        Node hare = first;
        while (hare != null && hare.next != null) {
            turtle = turtle.next;
            hare = hare.next.next;
            if (turtle == hare) {
                result = true;
                break;
            }
        }
        return result;
    }
}
