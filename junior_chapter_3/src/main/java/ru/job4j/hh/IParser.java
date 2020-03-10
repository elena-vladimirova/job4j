package ru.job4j.hh;

import java.util.Date;
import java.util.List;

/**
 * Интерфейс для сбора информации о вакансиях
 */
public interface IParser {

    public List<Vacancy> collect(String condition , Date minDate);

}
