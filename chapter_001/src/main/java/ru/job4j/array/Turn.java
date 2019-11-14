package ru.job4j.array;

public class Turn {
    public int[] back(int[] array) {
        int temp;
        int bound = array.length - 1;
        for (int i = 0; i <= Math.round(bound/2); i++) {
            temp = array[i];
            array[i] = array[bound-i];
            array[bound-i] = temp;
        }
        return array;
    }
}