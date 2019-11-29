package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenFindByNameThenReturnItems() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test1");
        Item item3 = new Item("test3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Item[] expected = new Item[100];
        expected[0] = item1;
        expected[1] = item2;
        Item[] result = tracker.findByName("test1");
        assertThat(result, is(expected));
    }

    @Test
    public void whenFindAllThenReturnItems() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Item[] expected = {item1, item2, item3};
        Item[] result = tracker.findAll();
        assertThat(result, is(expected));
    }

    @Test
    public void whenFindAllThenReturnNull() {
        Tracker tracker = new Tracker();
        Item[] expected = null;
        Item[] result = tracker.findAll();
        assertThat(result, is(expected));
    }

    @Test
    public void whenReplaceThenReplaced() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        boolean result = tracker.replace(item1.getId(), new Item("replaced"));
        String expected = "replaced";
        assertThat(tracker.findByName("replaced")[0].getName(), is(expected));
    }

    @Test
    public void whenDelete() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        boolean result = tracker.delete(tracker.findByName("test1")[0].getId());
        Item[] expected = {item2, item3};
        assertThat(expected, is(tracker.findAll()));
    }
}