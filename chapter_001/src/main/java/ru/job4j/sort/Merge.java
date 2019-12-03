package ru.job4j.sort;

import java.util.Arrays;

public class Merge {

    public int[] merge(int[] left, int[] right) {
        int[] merged = new int[left.length + right.length];
        int idxMerged = 0;
        int idxLeft = 0;
        int idxRight = 0;
        while (idxMerged < left.length + right.length) {
            if ((idxRight >= right.length) || (idxLeft < left.length && left[idxLeft] <= right[idxRight])) {
                merged[idxMerged] = left[idxLeft];
                idxLeft++;
            } else {
                merged[idxMerged] = right[idxRight];
                idxRight++;
            }
            idxMerged++;
        }
        return merged;
    }

    public static void main(String[] args) {
        Merge process = new Merge();
        int[] rsl = process.merge(
                new int[]{1, 3, 5},
                new int[]{2, 4}
        );
        System.out.println(Arrays.toString(rsl));
    }
}