package ru.job4j.lambda;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GroupTest {

    @Test
    public void whenLinearFunction() {
        Student student1 = new Student("Lena", Set.of("Java", "Music", "Oracle"));
        Student student2 = new Student("Anna", Set.of("Java"));
        Student student3 = new Student("Dima", Set.of("Java", "Music"));
        Map<String, Set<String>> result = Group.sections(List.of(student1, student2, student3));
        Map<String, Set<String>> expected = Map.of(
                "Java", Set.of("Lena", "Anna", "Dima"),
                "Music", Set.of("Lena", "Dima"),
                "Oracle", Set.of("Lena")
        );
        assertThat(result, is(expected));
    }

}
