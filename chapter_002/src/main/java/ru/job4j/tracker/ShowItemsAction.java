package ru.job4j.tracker;

public class ShowItemsAction implements UserAction {
    @Override
    public String name() {
        return "=== Show items ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        for (Item i : tracker.findAll()) {
            System.out.println(i);
        }
        return true;
    }
}
