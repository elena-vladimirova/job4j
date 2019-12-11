package ru.job4j.sort;

import java.util.Arrays;

public class Machine {
    private final int[] COINS = {10, 5, 2, 1};

    public int[] change(int money, int price) {
        int[] rsl = new int[100];
        int rslIndex = 0;
        int coinsIndex = 0;
        int change = money - price;
        while (change > 0) {
            if (change - COINS[coinsIndex] >= 0) {
                change -= COINS[coinsIndex];
                rsl[rslIndex++] = COINS[coinsIndex];
            } else {
                coinsIndex++;
            }
        }
        return Arrays.copyOf(rsl, rslIndex);
    }
}