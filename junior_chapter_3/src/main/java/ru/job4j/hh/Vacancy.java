package ru.job4j.hh;


import java.util.Date;

/**
 * Класс для работы с объектами вакансий.
 */
public class Vacancy {

    private String name;
    private String text;
    private String link;
    private Date dt;

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public Vacancy(String name, String text, String link, Date dt) {
        this.name = name;
        this.text = text;
        this.link = link;
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "Vacancy{"
                + "name='" + name + '\''
                + ", text='" + text + '\''
                + ", link='" + link + '\''
                + ", dt=" + dt
                + '}';
    }
}
