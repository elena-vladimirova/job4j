package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int result = 0;
        if (o1.length() == o2.length()) {
            result = o2.compareTo(o1);
        } else {
                 if (o1.startsWith(o2)) {
                     result = 1;
                 } else if (o2.startsWith(o1)) {
                     result = -1;
                 } else {
                     result = o2.compareTo(o1);
                 }
               }
        return result;
    }
}