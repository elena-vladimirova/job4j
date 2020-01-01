package ru.job4j.lambda;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Group {

    public static Map<String, Set<String>> sections(List<Student> students) {
        return students.stream()
                       .flatMap(s -> s.getUnits().stream().map(u -> new Holder(u, s.getName())))
                       .collect(Collectors.groupingBy(Holder::getKey,
                                                      Collectors.mapping(Holder::getValue, Collectors.toSet())));
    }
}