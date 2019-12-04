package ru.job4j.tracker;

public class FindItemByNameAction implements UserAction {
    @Override
    public String name() {
        return "=== Find items by name ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("=== Find items by name ====");
        String nameToFind = input.askStr("Enter name: ");
        for (Item i : tracker.findByName(nameToFind)) {
            System.out.println(i);
        }
        return true;
    }
}
