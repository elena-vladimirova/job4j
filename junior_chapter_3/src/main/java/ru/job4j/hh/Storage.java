package ru.job4j.hh;

import java.io.InputStream;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Класс для работы с БД (запросы к БД, запись информации о вакансиях)
 */
public class Storage implements AutoCloseable {

    private Connection connection;

    public Storage(String prop) {
        init(prop);
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    private boolean init(String prop) {
        try (InputStream in = Storage.class.getClassLoader().getResourceAsStream(prop)) {

            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(
                        "create table if not exists vacancies (\n"
                                + "id serial NOT NULL primary key,\n"
                                + "name varchar(300),\n"
                                + "text varchar(8000),\n"
                                + "link varchar(300),\n"
                                + "dt timestamp\n"
                                + ");");
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    public void insert(List<Vacancy> vacancies) {
        String insert = "insert into vacancies(name, text, link, dt)\n"
                           + "select ?, ?, ?, ?::timestamp\n"
                           +     "where not exists (select 1 from vacancies where name = ?);";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try (PreparedStatement pstmt = connection.prepareStatement(insert)) {
            Iterator<Vacancy> it = vacancies.iterator();
            while (it.hasNext()) {
                Vacancy vacancy = it.next();
                pstmt.setString(1, vacancy.getName());
                pstmt.setString(2, vacancy.getText());
                pstmt.setString(3, vacancy.getLink());
                pstmt.setString(4, dateFormat.format(vacancy.getDt()));
                pstmt.setString(5, vacancy.getName());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Date getMaxDate() {
        Date dt = null;
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("select max(dt) from\n" +
                                                    "(select dt from vacancies union select DATE_TRUNC('year', now()::timestamp)) t");
            if (rs.next()) {
                dt = rs.getDate(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dt;
    }

}
