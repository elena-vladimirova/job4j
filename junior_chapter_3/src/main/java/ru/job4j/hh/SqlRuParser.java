package ru.job4j.hh;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.*;
import ru.job4j.principle004.UsageLog4j2;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.DateBuilder.*;

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
 * Класс SqlRuParser парсит страницу вакансий сайта SQL.ru, формирует объекты Vacancy и вызывает методы записи вакансий в БД.
 *
 */
public class SqlRuParser /*implements Job*/ {

    private static final Logger LOG = LogManager.getLogger(UsageLog4j2.class.getName());

    Storage storage;

    public SqlRuParser(Storage storage) {
        this.storage = storage;
    }

    /**
     * Метод сбора вакансий.
     * @return Список вакансий.
     * @throws Exception
     */
    public List<Vacancy> collect() throws Exception {
        LOG.info("Запущен метод сбора вакансий");
        List<Vacancy> listVacancies = new LinkedList<>();
        // URL SQL.ru с параметрами поиска Java вакансий
        Document SqlRu = Jsoup.connect("https://www.sql.ru/forum/actualsearch.aspx?search=Java~Script&sin=1&bid=9&a=&ma=0&dt=356&s=1&so=2&pg=1").get();
        // Элементы поля "Тема" основной таблицы страницы. Содержат ссылки на вакансии
        Elements vacancies = SqlRu.select(".postslisttopic");
        for (Element vacancy : vacancies) {
            // Ссылка на вакансию
            Element vacancyLink = vacancy.select("a").first();
            // Дата создания вакансии
            Date dt = parseDate(vacancy.nextElementSiblings().get(4).text());
            if (new java.sql.Date(dt.getTime()).before(storage.getMaxDate())) {
                break;
            }
            String link = vacancyLink.attr("href");
            String name = vacancyLink.text();
            // Переход по ссылке на вакансию
            Document SqlRuVacancy = Jsoup.connect(vacancyLink.attr("href")).get();
            // Текст вакансии
            Element vacancyText = SqlRuVacancy.select(".msgBody").get(1);
            String text = vacancyText.text();
            Vacancy vacancyObj = new Vacancy(name, text, link, dt);
            listVacancies.add(vacancyObj);
        }
        return listVacancies;
    }

    public void store(List<Vacancy> vacancies) {
        storage.insert(vacancies);
    }

    private Date parseDate(String dt) throws ParseException {
        DateFormatSymbols symbols = new DateFormatSymbols(
                                          new Locale("ru", "RU"));
        symbols.setShortMonths(new String[] {"янв", "фев", "мар", "апр", "май", "июн",
                                             "июл", "авг", "сен", "окт", "ноя", "дек"});
        SimpleDateFormat ft = new SimpleDateFormat ("dd MMM yy, HH:mm", symbols);
        return ft.parse(dt);
    }

    // Запуск сбора вакансий
/*    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String properties = dataMap.getString("properties");

        SqlRuParser parser = new SqlRuParser(new Storage(properties));
        try {
            parser.store(parser.collect());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    public static void main(String[] args) throws Exception {

        // Запуск с параметром C:\projects\job4j\junior_chapter_3\src\main\resources\app.properties

/*        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        Scheduler sched = schedFact.getScheduler();
        sched.start();

        JobDetail job = newJob(SqlRuParser.class)
                .withIdentity("myJob", "group1")
                .usingJobData("properties", args[0])
                .build();

        Trigger  trigger = newTrigger()
                .withIdentity("trigger3", "group1")
                .withSchedule(cronSchedule("0 0/2 0-23 * * ?")) // Запуск каждую минуту с 0 до 23 часов
                .forJob("myJob", "group1")
                .startNow()
                .build();

        sched.scheduleJob(job, trigger);*/

        SqlRuParser parser = new SqlRuParser(new Storage(args[0]));
        try {
            parser.store(parser.collect());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

