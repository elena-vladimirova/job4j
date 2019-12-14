package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ItemTest {

    @Test
    public void whenSortAsc() {
        Item item1 = new Item("1) Первая задача");
        Item item2 = new Item("2) Вторая задача");
        Item item3 = new Item("3) Третья задача");
        List<Item> listItem = Arrays.asList(item2, item3, item1);
        Collections.sort(listItem);
        List<Item> expected = Arrays.asList(item1, item2, item3);
        assertThat(expected, is(listItem));
    }

    @Test
    public void whenSortDesc() {
        Item item1 = new Item("1) Первая задача");
        Item item2 = new Item("2) Вторая задача");
        Item item3 = new Item("3) Третья задача");
        List<Item> listItem = Arrays.asList(item2, item3, item1);
        Collections.sort(listItem, Collections.reverseOrder());
        List<Item> expected = Arrays.asList(item3, item2, item1);
        assertThat(expected, is(listItem));
    }

}
