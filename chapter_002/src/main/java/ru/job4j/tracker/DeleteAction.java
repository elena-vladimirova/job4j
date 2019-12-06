package ru.job4j.tracker;

public class DeleteAction extends BaseAction {

    public DeleteAction(){
        super("=== Delete item ====");
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String idToDelete = input.askStr("Enter id: ");
        boolean deleted = tracker.delete(idToDelete);
        System.out.println(deleted ? "Item deleted" : "Item not deleted");
        return true;
    }
}
