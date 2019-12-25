package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Консольное приложение для работы с классом Tracker.
 *
 * @author Elena Vladimirova (avtobusova@mail.ru)
 */
public class StartUI {

    /**
     * Метод выподит меню и выполняет запросы пользователя
     *
     * @param input   интерфейс для работы с системой ввода-вывода
     * @param tracker объект Tracker
     * @param actions список
     * @param output параметр указывает, куда выводить данные
     */
    public void init(Input input, Tracker tracker, UserAction[] actions, Consumer<String> output) {

        boolean run = true;
        while (run) {
            this.showMenu(actions, output);
            int select = input.askInt("Select: ", actions.length);
            if (select < actions.length && select >= 0) {
                UserAction action = actions[select];
                run = action.execute(input, tracker);
            } else {
                output.accept("There is no such menu item: " + select);
            }
        }
    }

    /**
     * Метод выподит меню
     */
    private void showMenu(UserAction[] actions, Consumer<String> output) {
        output.accept("Menu.");
        for (int index = 0; index < actions.length; index++) {
            output.accept(index + ". " + actions[index].name());
        }
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(),
                new ShowItemsAction(),
                new EditAction(),
                new DeleteAction(),
                new FindItemByIdAction(),
                new FindItemByNameAction(),
                new ExitAction()
        };
        new StartUI().init(validate, tracker, actions, System.out::println);
    }
}