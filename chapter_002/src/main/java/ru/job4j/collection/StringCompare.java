package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result = 0;
        int size = Math.max(left.length(), right.length());
        char[] leftChar = String.format("%-" + size + "s", left).toCharArray();
        char[] rightChar = String.format("%-" + size + "s", right).toCharArray();
        for (int i = 0; i < size; i++) {
            result = Character.compare(leftChar[i], rightChar[i]);
            if (result != 0) {
                break;
            }
        }
        return result;
    }
}