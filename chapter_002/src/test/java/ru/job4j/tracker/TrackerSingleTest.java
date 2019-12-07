package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerSingleTest {

    private Item item = new Item("test");

    @Test
    public void whenCreateTwoTrackerSingle1() {
        TrackerSingle1 tracker = TrackerSingle1.INSTANCE;
        tracker.add(item);
        TrackerSingle1 trackerDuplicate = TrackerSingle1.INSTANCE;
        Item result = trackerDuplicate.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenCreateTwoTrackerSingle2() {
        TrackerSingle2 tracker = TrackerSingle2.getInstance();
        tracker.add(item);
        TrackerSingle2 trackerDuplicate = TrackerSingle2.getInstance();
        Item result = trackerDuplicate.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenCreateTwoTrackerSingle3() {
        TrackerSingle3 tracker = TrackerSingle3.getInstance();
        tracker.add(item);
        TrackerSingle3 trackerDuplicate = TrackerSingle3.getInstance();
        Item result = trackerDuplicate.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenCreateTwoTrackerSingle4() {
        TrackerSingle4 tracker = TrackerSingle4.getInstance();
        tracker.add(item);
        TrackerSingle4 trackerDuplicate = TrackerSingle4.getInstance();
        Item result = trackerDuplicate.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }
}
