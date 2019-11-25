package ru.job4j.condition;

public class Max {

    public static int max(int left, int right) {
        boolean condition = left >= right;
        int result = condition ? left : right;
        return result;
    }

    public static int max(int num1, int num2, int num3) {
        int result = max(max(num1, num2), num3);
        return result;
    }

    public static int max(int num1, int num2, int num3, int num4) {
        int result = max(max(num1, num2, num3), num4);
        return result;
    }

}
