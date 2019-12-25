package ru.job4j.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SchoolTest {

    @Test
    public void whenClassA() {
        School school = new School();
        Student s1 = new Student(10);
        Student s2 = new Student(20);
        Student s3 = new Student(60);
        Student s4 = new Student(65);
        Student s5 = new Student(100);
        List<Student> list = Arrays.asList(s1, s2, s3, s4, s5);
        List<Student> result = school.collect(list, s -> s.getScore() > 70 && s.getScore() <= 100);
        List<Student> expected = Arrays.asList(s5);
        assertThat(result, is(expected));
    }
    @Test
    public void whenClassB() {
        School school = new School();
        Student s1 = new Student(10);
        Student s2 = new Student(20);
        Student s3 = new Student(60);
        Student s4 = new Student(65);
        Student s5 = new Student(100);
        List<Student> list = Arrays.asList(s1, s2, s3, s4, s5);
        List<Student> result = school.collect(list, s -> s.getScore() > 50 && s.getScore() <= 70);
        List<Student> expected = Arrays.asList(s3, s4);
        assertThat(result, is(expected));
    }
    @Test
    public void whenClassC() {
        School school = new School();
        Student s1 = new Student(10);
        Student s2 = new Student(20);
        Student s3 = new Student(60);
        Student s4 = new Student(65);
        Student s5 = new Student(100);
        List<Student> list = Arrays.asList(s1, s2, s3, s4, s5);
        List<Student> result = school.collect(list, s -> s.getScore() >= 0 && s.getScore() <= 50);
        List<Student> expected = Arrays.asList(s1, s2);
        assertThat(result, is(expected));
    }
}
