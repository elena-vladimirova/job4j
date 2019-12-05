package ru.job4j.tracker;

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
     * @param actions список действий
     */
    public void init(Input input, Tracker tracker, UserAction[] actions) {

        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ", actions.length);
            if (select < actions.length && select >= 0) {
                UserAction action = actions[select];
                run = action.execute(input, tracker);
            } else {
                System.out.println("There is no such menu item: " + select);
                }
        }
    }

    /**
     * Метод выподит меню
     */
    private void showMenu(UserAction[] actions) {
        System.out.println("Menu.");
        for (int index = 0; index < actions.length; index++) {
            System.out.println(index + ". " + actions[index].name());
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
        new StartUI().init(validate, tracker, actions);
    }
}