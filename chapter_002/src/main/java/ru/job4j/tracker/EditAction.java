package ru.job4j.tracker;

public class EditAction implements UserAction {
    @Override
    public String name() {
        return "=== Edit item ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String replacedId = input.askStr("Enter id: ");
        String changedName = input.askStr("Enter a new item name: ");
        boolean replaced = tracker.replace(replacedId, new Item(changedName));
        System.out.println(replaced  ? "Item changed" : "Item not changed");
        return true;
    }
}
