package ru.job4j.condition;

import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {

    @Test
    public void whenTriangleExists() {

        Triangle t = new Triangle(new Point(0, 0), new Point(0, 2), new Point(2, 0));
        double result = t.area();
        double expected = 2;

        Assert.assertEquals(expected, result, 0.01);
    }

    @Test
    public void whenTriangleNotExists() {

        Triangle t = new Triangle(new Point(0, 0), new Point(0, 2), new Point(0, 1));
        double result = t.area();
        double expected = -1;

        Assert.assertEquals(expected, result, 0.01);
    }
}
