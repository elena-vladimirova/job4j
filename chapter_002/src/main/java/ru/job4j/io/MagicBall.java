package ru.job4j.io;

import java.util.Random;
import java.util.Scanner;

public class MagicBall {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Я великий Оракул. Что ты хочешь узнать? ");
        String name = s.nextLine();
        int answer = new Random().nextInt(3);
        System.out.println(answer == 1 ? "Да" : (answer == 2 ? "Нет" : "Может быть"));
    }
}
