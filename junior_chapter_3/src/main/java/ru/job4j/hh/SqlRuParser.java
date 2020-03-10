package ru.job4j.hh;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Класс для работы с сайтом SQL.ru
 */
public class SqlRuParser implements IParser {

    private String url = "https://www.sql.ru/forum/actualsearch.aspx?search=%s&sin=1&bid=9&a=&ma=0&dt=356&s=1&so=2&pg=1";

    /**
     * Метод сбора вакансий.
     * @return Список вакансий.
     */
    public List<Vacancy> collect(String condition, Date minDate) {
        List<Vacancy> listVacancies = new LinkedList<>();
        try {
            String urlCondition = String.format(url, condition);
            // URL SQL.ru с параметрами поиска Java вакансий
            Document SqlRu = Jsoup.connect(urlCondition).get();
            // Список страниц
            Iterator<Element> pages = SqlRu.select(".forumtable_results").get(1).select("a").iterator();
            boolean hasNewVacancies = add(listVacancies, SqlRu);
            while (hasNewVacancies && pages.hasNext()) {
                SqlRu = Jsoup.connect("https://www.sql.ru/forum/" + pages.next().attr("href")).get();
                hasNewVacancies = add(listVacancies, SqlRu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVacancies;
    }

    private boolean add(List<Vacancy> listVacancies, Document SqlRu) {
        // Элементы поля "Тема" основной таблицы страницы. Содержат ссылки на вакансии
        Elements vacancies = SqlRu.select(".postslisttopic");
        for (Element vacancy : vacancies) {
            try {
                // Ссылка на вакансию
                Element vacancyLink = vacancy.select("a").first();
                // Дата создания вакансии
                Date dt = parseDate(vacancy.nextElementSiblings().get(4).text());
            /*if (new java.sql.Date(dt.getTime()).before(storage.getMaxDate())) {
                return false;
            }*/
                String link = vacancyLink.attr("href");
                String name = vacancyLink.text();
                // Переход по ссылке на вакансию
                Document SqlRuVacancy = Jsoup.connect(vacancyLink.attr("href")).get();
                // Текст вакансии
                Element vacancyText = SqlRuVacancy.select(".msgBody").get(1);
                String text = vacancyText.text();
                Vacancy vacancyObj = new Vacancy(name, text, link, dt);
                listVacancies.add(vacancyObj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    private Date parseDate(String dt) {
        Date result = null;
        DateFormatSymbols symbols = new DateFormatSymbols(
                new Locale("ru", "RU"));
        symbols.setShortMonths(new String[] {"янв", "фев", "мар", "апр", "май", "июн",
                "июл", "авг", "сен", "окт", "ноя", "дек"});
        SimpleDateFormat ft = new SimpleDateFormat ("dd MMM yy, HH:mm", symbols);
        try {
            result = ft.parse(dt);
        } catch (ParseException e) {
            result = new Date();
        }
        return result;
    }
}
