package ru.job4j.oop;

public class College {
    public static void main(String[] args) {
        // понижающее приведение (down casting)
        Student student = new Freshman();
        Object o = student;
    }
}
