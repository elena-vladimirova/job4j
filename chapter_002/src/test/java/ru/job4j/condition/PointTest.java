package ru.job4j.condition;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    @Test
    public void when2d() {
        Point p1 = new Point (0, 0);
        Point p2 = new Point (0, 2);
        double expected = 2;
        double out = p1.distance(p2);
        Assert.assertEquals(expected,  out, 0.01);
    }

    @Test
    public void when3d() {
        Point p1 = new Point (0, 0, 0);
        Point p2 = new Point (0, 0, 2);
        double expected = 2;
        double out = p1.distance3d(p2);
        Assert.assertEquals(expected,  out, 0.01);
    }
}
