package ru.job4j.stream;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class School {

    public List<Student> getStudentsList(List<Student> students, Predicate<Student> predict) {
        return students.stream().filter(predict).collect(Collectors.toList());
    }

    public Map<String, Student> getStudentsMap(List<Student> students) {
        return students.stream().collect(Collectors.toMap(s -> s.getName(), s -> s));
    }

    //Метод должен вернуть список студентов у которых балл аттестата больше bound
    public List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .flatMap(Stream::ofNullable)
                .sorted(new SortStudentsByScoreDesc())
                .takeWhile(s -> s.getScore() > bound)
                .collect(Collectors.toList());
    }
}
