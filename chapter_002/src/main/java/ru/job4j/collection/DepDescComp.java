package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int result = o2.compareTo(o1);
        if (o1.length() != o2.length()) {
            int size = Math.min(o1.length(), o2.length());
            String o1Trunc = o1.substring(0, size);
            String o2Trunc = o2.substring(0, size);
            if (o1Trunc.equals(o2Trunc) && o1.length() > o2.length()) {
                result = 1;
            } else if (o1Trunc.equals(o2Trunc) && o1.length() < o2.length()) {
                result = -1;
            }
        }
        return result;
    }
}