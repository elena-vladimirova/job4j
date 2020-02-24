package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Класс для работы с заявками.
 *
 * @author Elena Vladimirova (avtobusova@mail.ru)
 */
public class TrackerSQL implements ITracker, AutoCloseable {

    private Connection connection;

    public TrackerSQL() {
        this.init();
    }

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {

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
                        "create table if not exists items (\n" +
                        "    id serial primary key,\n" +
                        "\tname varchar(4000)\n" +
                        ");");
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement prepStmt = connection.prepareStatement(
                "insert into items (name) values (?) returning id",
                Statement.RETURN_GENERATED_KEYS)) {
            prepStmt.setString(1, item.getName());
            prepStmt.executeUpdate();
            ResultSet rs = prepStmt.getGeneratedKeys();
            if (rs.next()) {
                item.setId(Long.toString(rs.getLong(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        int rowCount = 0;
        try (PreparedStatement prepStmt = connection.prepareStatement(
                "update items set name = ? where id = ?")) {
            prepStmt.setString(1, item.getName());
            prepStmt.setLong(2, Long.parseLong(id));
            rowCount = prepStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount > 0;
    }

    @Override
    public boolean delete(String id) {
        int rowCount = 0;
        try (PreparedStatement prepStmt = connection.prepareStatement(
                "delete from items where id = ?")) {
            prepStmt.setLong(1, Long.parseLong(id));
            rowCount = prepStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount > 0;
    }

    private List<Item> find(PreparedStatement prepStmt) throws SQLException {
        List<Item> result = new ArrayList<>();
        ResultSet rs = prepStmt.executeQuery();
        while(rs.next()) {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            result.add(new Item(Long.toString(id), name));
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = null;
        try (PreparedStatement prepStmt = connection.prepareStatement(
                "select id, name from items")) {
            result = find(prepStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findByName(String name) {
        List<Item> result = null;
        try (PreparedStatement prepStmt = connection.prepareStatement(
                "select id, name from items where name = ?")) {
            prepStmt.setString(1, name);
            result = find(prepStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        List<Item> result = null;
        try (PreparedStatement prepStmt = connection.prepareStatement(
                "select id, name from items where id = ?")) {
            prepStmt.setLong(1, Long.parseLong(id));
            result = find(prepStmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result != null ? result.get(0) : null;
    }

    public static void main(String[] args) {
        ITracker tracker = new TrackerSQL();
        Item item = new Item("first");
        tracker.add(item);
        System.out.println(item.getId());
        System.out.println(tracker.replace("1", new Item("first_upd") ));
        tracker.add(new Item("second"));
        tracker.add(new Item("first_upd"));
        tracker.add(new Item("fourth"));
        tracker.delete("2");

        tracker.findAll().forEach(i->System.out.println(i));
        tracker.findByName("first_upd").forEach(i->System.out.println(i));
        System.out.println(tracker.findById("4"));
    }
}
