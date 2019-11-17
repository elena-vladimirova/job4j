package ru.job4j.array;

public class Defragment {
    public static String[] compress(String[] array) {
        int innerIndex;
        for (int index = 0; index < array.length; index++) {
            if (array[index] == null) {
                innerIndex = index;
                do {
                    if (array[innerIndex] != null){
                        array[index] = array[innerIndex];
                        array[innerIndex] = null;
                        break;
                    }
                    innerIndex++;
                } while (innerIndex < array.length);
            }
        }
        return array;
    }

}