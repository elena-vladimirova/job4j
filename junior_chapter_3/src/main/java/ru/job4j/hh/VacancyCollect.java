package ru.job4j.hh;

/**
 * Приложение парсер должно заходить на сайт sql.ru в раздел работа и собирать Java вакансии.
 *
 * Ваша задача
 *
 * 1. Реализовать модуль сборки анализа данных с sql.ru.
 * 2. Система должна использовать Jsoup для парсинга страниц.
 * 3. Система должна запускаться раз в день.
 * Для этого нужно использовать библиотеку quartz.
 * 4. Система должна собирать данные только про вакансии java. учесть что JavaScript не подходит. как и Java Script.
 * 5. Данные должны храниться в базе данных.
 * 6. Учесть дубликаты. Вакансии с одинаковым именем считаются дубликатами.
 * 7. Учитывать время последнего запуска. если это первый запуск. то нужно собрать все объявления с начало года.
 * 8. В системе не должно быть вывода, либо ввода информации. все настройки должны быть в файле. app.properties.
 * 9. для вывода нужной информации использовать логгер log4j. Описание здесь 4.1. Log4j 2. Логирование системы.
 * 10. Пример запуска приложения.
 *
 * java -jar SqlRuParser app.properties
 *
 * Класс VacancyCollect инициирует процесс сбора и сохранения информации о вакансиях
 *
 */
public class VacancyCollect {

    /**
     * Условие поиска
     */
    private String condition;
    IStorage storage;
    IParser parser;

    public VacancyCollect(String condition, IStorage storage, IParser parser) {
        this.condition = condition;
        this.storage = storage;
        this.parser = parser;
    }

    public void collect() {
        storage.store(
                parser.collect(condition, storage.getMaxDate())
        );
    }

    public static void main(String[] args) {
        // Вызов с параметром Java~Script
        VacancyCollect parser = new VacancyCollect(args[0], new Storage(), new SqlRuParser());
        parser.collect();
    }
}

