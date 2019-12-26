package ru.job4j.stream;

public class Student {

    private String name;
    private int score;

    public Student(int score) {
        this("noName", score);
    }

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
