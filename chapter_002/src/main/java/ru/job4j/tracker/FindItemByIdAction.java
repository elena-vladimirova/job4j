package ru.job4j.tracker;

public class FindItemByIdAction extends BaseAction {

    public FindItemByIdAction() {
        super("=== Find item by Id ====");
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("=== Find item by Id ====");
        String idToFind = input.askStr("Enter id: ");
        System.out.println(tracker.findById(idToFind));
        return true;
    }
}
