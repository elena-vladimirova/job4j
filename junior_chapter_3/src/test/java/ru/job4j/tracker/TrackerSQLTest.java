package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class TrackerSQLTest {


    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item("test");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(item.getName()));
        }
    }

    @Test
    public void whenFindByNameThenReturnItems() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item1 = new Item("test1");
            Item item2 = new Item("test1");
            Item item3 = new Item("test3");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            List<Item> result = tracker.findByName("test1");
            List<Item> expected = Arrays.asList(item1, item2);
            assertThat(result, is(expected));
        }
    }

    @Test
    public void whenFindByNameThenReturnNull() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> expected = new ArrayList();
        List<Item> result = tracker.findByName("test4");
        assertThat(expected, is(result));
    }}

    @Test
    public void whenFindAllThenReturnItems() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> expected = Arrays.asList(item1, item2, item3);
        List<Item> result = tracker.findAll();
        assertThat(result, is(expected));
    }}

    @Test
    public void whenFindAllThenReturnNull() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
        List<Item> expected = new ArrayList();
        List<Item> result = tracker.findAll();
        assertThat(result, is(expected));
    }}

    @Test
    public void whenReplaceThenReplaced() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        boolean result = tracker.replace(item1.getId(), new Item("replaced"));
        String expected = "replaced";
        assertThat(tracker.findByName("replaced").get(0).getName(), is(expected));
    }}

    @Test
    public void whenDelete() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        boolean result = tracker.delete(tracker.findByName("test1").get(0).getId());
        List<Item> expected = Arrays.asList(item2, item3);
        assertThat(expected, is(tracker.findAll()));
    }}
}