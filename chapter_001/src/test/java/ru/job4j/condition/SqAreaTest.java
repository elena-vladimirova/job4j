package ru.job4j.condition;

import org.junit.Assert;
import org.junit.Test;

public class SqAreaTest {

    @Test
    public void squareWhenParams4and1ThenResult1() {

        double result = SqArea.square(4,  1);
        double expected = 1;

        Assert.assertEquals(expected, result, 0.01);
    }
    @Test
    public void squareWhenParams6and2ThenResult2() {

        double result = SqArea.square(6,  2);
        double expected = 2;

        Assert.assertEquals(expected, result, 0.01);
    }
}
