package ru.job4j.hh;

import java.sql.Date;
import java.util.List;

/**
 * Интерфейс для сохранения информации о собранных вакансиях и получения даты отсечения
 */
public interface IStorage {

    public void store(List<Vacancy> vacancies);
    public Date getMaxDate();

}
