package ru.job4j.loop;

public class CheckPrimeNumber {
    public boolean check(int num) {
        boolean prime = (num != 1);
        for (int i = num - 1; i > 1; i--) {
            if ((num % i) == 0) {
                prime = false;
                break;
            }
        }
        return prime;
    }
}