package ru.job4j.stream;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {

    public List<Student> getStudentsList(List<Student> students, Predicate<Student> predict) {
        return students.stream().filter(predict).collect(Collectors.toList());
    }

    public Map<String, Student> getStudentsMap(List<Student> students) {
        return students.stream().collect(Collectors.toMap(s -> s.getName(), s -> s));
    }

}
