package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Matrix {

    public static List<Integer> matrixToList(Integer[][] matrix) {
        return Stream.of(matrix).flatMap(row -> Arrays.stream(row)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Integer[][] matrix = new Integer[2][2];
        matrix[0] = new Integer[] {10, 20};
        matrix[1] = new Integer[] {30, 40};
        matrixToList(matrix).forEach(System.out::print);
    }

}
