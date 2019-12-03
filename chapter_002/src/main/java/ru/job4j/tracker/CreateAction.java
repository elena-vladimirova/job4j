package ru.job4j.tracker;

public class CreateAction implements UserAction {

    @Override
    public String name() {
        return "=== Add new Item ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String newItemName = input.askStr("Enter name: ");
        System.out.println(tracker.add(new Item(newItemName)));
        return true;
    }
}
