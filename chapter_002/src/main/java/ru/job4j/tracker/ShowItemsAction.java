package ru.job4j.tracker;

public class ShowItemsAction extends BaseAction {

    public ShowItemsAction() {
        super("=== Show items ====");
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        for (Item i : tracker.findAll()) {
            System.out.println(i);
        }
        return true;
    }
}
