package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        ITracker tracker = new Tracker();
        Item item = new Item("test");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenFindByNameThenReturnItems() {
        ITracker tracker = new Tracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test1");
        Item item3 = new Item("test3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> expected = new ArrayList();
        expected.add(item1);
        expected.add(item2);
        List<Item> result = tracker.findByName("test1");
        assertThat(result, is(expected));
    }

    @Test
    public void whenFindByNameThenReturnNull() {
        ITracker tracker = new Tracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> expected = new ArrayList();
        List<Item> result = tracker.findByName("test4");
        assertThat(expected, is(result));
    }

    @Test
    public void whenFindAllThenReturnItems() {
        ITracker tracker = new Tracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> expected = Arrays.asList(item1, item2, item3);
        List<Item> result = tracker.findAll();
        assertThat(result, is(expected));
    }

    @Test
    public void whenFindAllThenReturnNull() {
        ITracker tracker = new Tracker();
        List<Item> expected = new ArrayList();
        List<Item> result = tracker.findAll();
        assertThat(result, is(expected));
    }

    @Test
    public void whenReplaceThenReplaced() {
        ITracker tracker = new Tracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        boolean result = tracker.replace(item1.getId(), new Item("replaced"));
        String expected = "replaced";
        assertThat(tracker.findByName("replaced").get(0).getName(), is(expected));
    }

    @Test
    public void whenDelete() {
        ITracker tracker = new Tracker();
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        boolean result = tracker.delete(tracker.findByName("test1").get(0).getId());
        List<Item> expected = Arrays.asList(item2, item3);
        assertThat(expected, is(tracker.findAll()));
    }
}