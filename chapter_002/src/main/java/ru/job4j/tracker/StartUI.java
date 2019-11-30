package ru.job4j.tracker;

import java.util.Scanner;

/**
 * Консольное приложение для работы с классом Tracker.
 *
 * @author Elena Vladimirova (avtobusova@mail.ru)
 */
public class StartUI {

    /**
     * Метод выподит меню и выполняет запросы пользователя
     * @param scanner входящий поток
     * @param tracker объект Tracker
     */
    public void init(Scanner scanner, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            System.out.println("Select: ");
            int select = Integer.valueOf(scanner.nextLine());
            switch (select) {
                case 0 :
                    System.out.println("=== Add new Item ====");
                    System.out.println("Enter name: ");
                    String newItemName = scanner.nextLine();
                    System.out.println(tracker.add(new Item(newItemName)));
                    break;
                case 1 :
                    System.out.println("=== Show all items ====");
                    for (Item i : tracker.findAll()) {
                        System.out.println(i);
                    }
                    break;
                case 2 :
                    System.out.println("=== Edit item ====");
                    System.out.println("Enter id: ");
                    String replacedId = scanner.nextLine();
                    System.out.println("Enter a new item name: ");
                    String changedName = scanner.nextLine();
                    boolean replaced = tracker.replace(replacedId, new Item(changedName));
                    System.out.println((replaced == true)?"Item changed":"Item not changed");
                    break;
                case 3 :
                    System.out.println("=== Delete item ====");
                    System.out.println("Enter id: ");
                    String idToDelete = scanner.nextLine();
                    boolean deleted = tracker.delete(idToDelete);
                    System.out.println((deleted == true)?"Item deleted":"Item not deleted");
                    break;
                case 4:
                    System.out.println("=== Find item by Id ====");
                    System.out.println("Enter id: ");
                    String idToFind = scanner.nextLine();
                    System.out.println(tracker.findById(idToFind));
                    break;
                case 5:
                    System.out.println("=== Find items by name ====");
                    System.out.println("Enter name: ");
                    String nameToFind = scanner.nextLine();
                    for (Item i : tracker.findByName(nameToFind)) {
                        System.out.println(i);
                    }
                    break;
                case 6 :
                    run = false;
                    break;
                default :
                    System.out.println("Invalid menu item");
            }
        }
    }

    /**
     * Метод выподит меню
     */
    private void showMenu() {
        System.out.println("Menu.\n" +
                           "0. Add new Item\n" +
                           "1. Show all items\n" +
                           "2. Edit item\n" +
                           "3. Delete item\n" +
                           "4. Find item by Id\n" +
                           "5. Find items by name\n" +
                           "6. Exit Program");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tracker tracker = new Tracker();
        new StartUI().init(scanner, tracker);
    }
}