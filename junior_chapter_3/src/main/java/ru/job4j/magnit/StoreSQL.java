package ru.job4j.magnit;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StoreSQL implements AutoCloseable {

    private final Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config;
        init();
    }

    public void init() {
        try {
            this.connect = DriverManager.getConnection(config.get("url"));

            String create = config.get("createEntry");
            try (Statement stmt = connect.createStatement()) {
                stmt.executeUpdate(create);
            }

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void delete() {
        String delete = "delete from entry";
        try (Statement stmt = connect.createStatement()) {
            stmt.executeUpdate(delete);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void generate(int size) {
        delete();
        String insert = "insert into entry (field) values(?)";
        try (PreparedStatement pstmt = connect.prepareStatement(insert)) {
            for (int i = 1; i <= size; i++) {
                pstmt.setInt(1, i);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public List<StoreXML.Entry> load() {
        List<StoreXML.Entry> result = new LinkedList<>();
        try (Statement stmt = connect.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from entry");
            while (rs.next()) {
                result.add(new StoreXML.Entry(rs.getInt("field")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}
