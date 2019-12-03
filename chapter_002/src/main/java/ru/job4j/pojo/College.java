package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student s = new Student();
        s.setName("Жгуть Иван Иванович");
        s.setGroup("П127");
        s.setMatriculationDate(new Date(2019, 11, 26));
        System.out.println(s.getName() + " " + s.getGroup() + " " + s.getMatriculationDate());
    }
}
