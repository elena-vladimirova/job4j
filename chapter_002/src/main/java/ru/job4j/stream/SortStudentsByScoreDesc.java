package ru.job4j.stream;

import java.util.Comparator;

public class SortStudentsByScoreDesc implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o2.getScore(), o1.getScore());
    }
}