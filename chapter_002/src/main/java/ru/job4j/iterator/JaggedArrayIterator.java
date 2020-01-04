package ru.job4j.iterator;

import java.util.Iterator;

public class JaggedArrayIterator implements Iterator {

    private final int[][] values;
    private int row = 0;
    private int col = 0;

    public JaggedArrayIterator(final int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return (row < values.length - 1)
               || (row == values.length - 1 && col < values[values.length - 1].length);
    }

    @Override
    public Object next() {
        int result = values[row][col];
        if (col == values[row].length - 1) {
            row++;
            col = 0;
        } else {
            col++;
        }
        return result;
    }

    public static void main(String[] args) {
        JaggedArrayIterator i = new JaggedArrayIterator(new int[][]{{1}, {3, 4}, {7}}); //(new int[][] {{1},{2, 3, 4, 5,},{6, 7}, {8, 9, 10, 11, 12, 13, 14}});
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}
