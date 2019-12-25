package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalcTest {
    @Test
    public void whenLinearFunction() {
        List<Double> result = Calc.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }
    @Test
    public void whenQuadraticFunction() {
        List<Double> result = Calc.diapason(5, 8, x ->  Math.pow(x, 2) + 1);
        List<Double> expected = Arrays.asList(26D, 37D, 50D);
        assertThat(result, is(expected));
    }
    @Test
    public void whenLogFunction() {
        List<Double> result = Calc.diapason(5, 8, x ->  Math.log(x));
        List<Double> expected = Arrays.asList(Math.log(5), Math.log(6), Math.log(7));
        assertThat(result, is(expected));
    }
}
