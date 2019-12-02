package ru.job4j.tracker;

/**
 * Консольное приложение для работы с классом Tracker.
 *
 * @author Elena Vladimirova (avtobusova@mail.ru)
 */
public class StartUI {

    /**
     * Метод выподит меню и выполняет запросы пользователя
     * @param input интерфейс для работы с системой ввода-вывода
     * @param tracker объект Tracker
     */
    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = input.askInt("Select: ");
            switch (select) {
                case 0 :
                    System.out.println("=== Add new Item ====");
                    String newItemName = input.askStr("Enter name: ");
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
                    String replacedId = input.askStr("Enter id: ");
                    String changedName = input.askStr("Enter a new item name: ");
                    boolean replaced = tracker.replace(replacedId, new Item(changedName));
                    System.out.println((replaced == true)?"Item changed":"Item not changed");
                    break;
                case 3 :
                    System.out.println("=== Delete item ====");
                    String idToDelete = input.askStr("Enter id: ");
                    boolean deleted = tracker.delete(idToDelete);
                    System.out.println((deleted == true)?"Item deleted":"Item not deleted");
                    break;
                case 4:
                    System.out.println("=== Find item by Id ====");
                    String idToFind = input.askStr("Enter id: ");
                    System.out.println(tracker.findById(idToFind));
                    break;
                case 5:
                    System.out.println("=== Find items by name ====");
                    String nameToFind = input.askStr("Enter name: ");
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
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}