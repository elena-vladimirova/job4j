package ru.job4j.array;

public class SortSelected {

    public static int[] sort(int[] data) {

        int min;
        int index;
        int temp;

        for (int i = 0; i < data.length - 1; i++){
            min = MinDiapason.findMin(data, i, data.length - 1);
            index = FindLoop.indexOf(data, min, i, data.length - 1);
            temp = data[i];
            data[i] = min;
            data[index] = temp;
        }

        return data;
    }
}