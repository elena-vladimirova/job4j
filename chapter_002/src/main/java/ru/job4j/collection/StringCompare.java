package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result = 0;
        int minLength = Math.min(left.length(), right.length());
        int idx = 0;
        while (idx < minLength && result == 0) {
            result = left.charAt(idx) - right.charAt(idx);
            idx++;
        }
        int deltaLength = left.length() - right.length();
        return (result == 0 && deltaLength != 0) ? deltaLength : result;
    }

    public static void main(String[] args) {
        System.out.println(new StringCompare().compare("123", "12345"));
    }

}