package ru.job4j.tracker;

public class CreateAction extends BaseAction {

    public CreateAction() {
        super("=== Add new Item ====");
    }

    @Override
    public boolean execute(Input input, ITracker tracker) {
        String newItemName = input.askStr("Enter name: ");
        System.out.println(tracker.add(new Item(newItemName)));
        return true;
    }
}
