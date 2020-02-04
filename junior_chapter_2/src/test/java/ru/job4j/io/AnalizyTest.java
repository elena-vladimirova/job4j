package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Test
    public void whenUnavailable() {
        Analizy.unavailable("src\\main\\resources\\test_log.txt", "src\\main\\resources\\unavailable.csv");
        try (BufferedReader read = new BufferedReader(new FileReader("src\\main\\resources\\unavailable.csv"))) {
            assertThat("10:57:01;10:59:01", is(read.readLine()));
            assertThat("11:01:02;11:02:02", is(read.readLine()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
