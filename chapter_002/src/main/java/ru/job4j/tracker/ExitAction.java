package ru.job4j.tracker;

public class ExitAction extends BaseAction {

    public ExitAction() {
        super("=== Exit ====");
    }

    @Override
    public boolean execute(Input input, ITracker tracker) {
        return false;
    }
}
