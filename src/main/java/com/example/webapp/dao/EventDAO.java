package com.example.webapp.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.webapp.model.Event;

public class EventDAO {
    private static final String DB_URL = "jdbc:sqlite:c:\\events\\event.db";

    public EventDAO() {
        try {
            Class.forName("org.sqlite.JDBC");
            createTableIfNotExists();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableIfNotExists() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS events (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "date TEXT NOT NULL," +
                    "location TEXT NOT NULL," +
                    "description TEXT NOT NULL," +
                    "type TEXT," +
                    "attendees INTEGER DEFAULT 0)";
            statement.executeUpdate(createTableSQL);
        }
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events";

        try (Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Event event = new Event(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("date"),
                        resultSet.getString("location"),
                        resultSet.getString("description"),
                        resultSet.getString("type"),
                        resultSet.getInt("attendees")
                );
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

    public void addEvent(Event event) {
        String sql = "INSERT INTO events (name, date, location, description, type, attendees) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, event.getName());
            preparedStatement.setString(2, event.getDate());
            preparedStatement.setString(3, event.getLocation());
            preparedStatement.setString(4, event.getDescription());
            preparedStatement.setString(5, event.getType());
            preparedStatement.setInt(6, event.getAttendees());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Event getEventById(int id) {
        String sql = "SELECT * FROM events WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("date"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("type"),
                        rs.getInt("attendees")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateEvent(Event event) {
        String sql = "UPDATE events SET name = ?, date = ?, location = ?, description = ?, type = ?, attendees = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, event.getName());
            stmt.setString(2, event.getDate());
            stmt.setString(3, event.getLocation());
            stmt.setString(4, event.getDescription());
            stmt.setString(5, event.getType());
            stmt.setInt(6, event.getAttendees());
            stmt.setInt(7, event.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEvent(int id) {
        String sql = "DELETE FROM events WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Event> getFilteredEvents(String type, String location, String date) {
        List<Event> events = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM events WHERE 1=1");

        if (type != null && !type.isEmpty()) {
            sql.append(" AND type LIKE ?");
        }
        if (location != null && !location.isEmpty()) {
            sql.append(" AND location LIKE ?");
        }
        if (date != null && !date.isEmpty()) {
            sql.append(" AND date = ?");
        }

        try (Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int index = 1;

            if (type != null && !type.isEmpty()) {
                stmt.setString(index++, "%" + type + "%");
            }
            if (location != null && !location.isEmpty()) {
                stmt.setString(index++, "%" + location + "%");
            }
            if (date != null && !date.isEmpty()) {
                stmt.setString(index++, date);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("date"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getString("type"),
                        rs.getInt("attendees")
                );
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }
}
