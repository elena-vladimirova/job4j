package ru.job4j.oop;

public class BallStory {
    public static void main(String[] args) {
        Ball b = new Ball();
        Hare h = new Hare();
        Wolf w = new Wolf();
        Fox f = new Fox();
        h.tryEat(b);
        w.tryEat(b);
        f.tryEat(b);
    }
}
