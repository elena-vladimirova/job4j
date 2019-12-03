package ru.job4j.io;

import java.util.Random;
import java.util.Scanner;

public class Matches {
    public static void main(String[] args) {
        int matches = 11;
        Scanner input = new Scanner(System.in);
        System.out.println("Игра 11. На столе лежат 11 спичек. Два игрока по очереди берут от 1 до 3 спичек. Выигрывает тот, кто забрал последние спички.");
        while (matches > 0) {
            System.out.println("Количество спичек: " + matches + " Ваш ход:");
            int playersMove = Integer.valueOf(input.nextLine());
            if (playersMove < 1 || playersMove > 3 || playersMove > matches) {
                System.out.println("Выбрано неверное количество спичек.");
            } else {
                matches -= playersMove;
                if (matches == 0) {
                    System.out.println("Вы победили");
                } else {
                    int progMove = Math.min((new Random().nextInt(2) + 1), matches);
                    System.out.println("Ход программы: " + progMove);
                    matches -= progMove;
                    if (matches == 0) {
                        System.out.println("Вы проиграли");
                    }
                }
            }
        }
    }
}
