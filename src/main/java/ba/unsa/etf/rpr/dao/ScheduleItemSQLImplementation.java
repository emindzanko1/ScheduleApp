package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Event;
import ba.unsa.etf.rpr.exceptions.ScheduleException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * MySQL Implementation of DAO
 * @author Emin Džanko
 */
public class ScheduleItemSQLImplementation implements ScheduleItemDao{
    private static ScheduleItemSQLImplementation instance = null;

    private Connection conn;
    private final PreparedStatement searchQuery, addQuery, newIdQuery, changeQuery, deleteQuery, allQuery, getByNameQuery;

    private ScheduleItemSQLImplementation() throws SQLException {
        Properties p = new Properties();
        try {
            p.load(ClassLoader.getSystemResource("application.properties").openStream());
            String url = p.getProperty("db.url");
            String username = p.getProperty("db.username");
            String password = p.getProperty("db.password");
            conn = DriverManager.getConnection(url, username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        searchQuery = conn.prepareStatement("SELECT * FROM ScheduleItem WHERE Item_ID=?");
        newIdQuery = conn.prepareStatement("SELECT MAX(Item_ID)+1 FROM ScheduleItem");
        addQuery = conn.prepareStatement("INSERT INTO ScheduleItem VALUES(?,?,?,?,?,?,?)");
        changeQuery = conn.prepareStatement("UPDATE ScheduleItem SET dayOfWeek=?, startTime=?, endTime=?, eventName=?, location=? WHERE Item_ID=?");
        deleteQuery = conn.prepareStatement("DELETE FROM ScheduleItem WHERE Item_ID=?");
        allQuery = conn.prepareStatement("SELECT * FROM ScheduleItem");
        getByNameQuery = conn.prepareStatement("SELECT * FROM ScheduleItem WHERE eventName=?");
    }

    public static ScheduleItemSQLImplementation getInstance() throws SQLException {
        if (instance == null) instance = new ScheduleItemSQLImplementation();
        return instance;
    }

    public static void removeInstance() throws SQLException {
        if (instance == null) return;
        instance.conn.close();
        instance = null;
    }
    @Override
    public Event get(int id) throws ScheduleException {
        Event event = new Event();
        try {
            searchQuery.setString(1, String.valueOf(id));
            ResultSet rs = searchQuery.executeQuery();
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            throw new ScheduleException("Failed getting schedule item by id.", e);

        }
        return event;
    }

    @Override
    public List<Event> getAll() throws ScheduleException {
        ArrayList<Event> events = new ArrayList<>();
        try {
            ResultSet rs = allQuery.executeQuery();
            while(rs.next()) {
                events.add(new Event(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            throw new ScheduleException("Failed getting all schedule items.", e);
        }
        return events;
    }

    @Override
    public void save(Event event) throws ScheduleException {
        try {
            ResultSet rs = newIdQuery.executeQuery();
            if(rs.next())
                event.setId(rs.getInt(1));
            else
                event.setId(1);

            addQuery.setInt(1, event.getId());
            addQuery.setInt(2, event.getScheduleId());
            addQuery.setString(3, event.getDayOfWeek());
            addQuery.setString(4, event.getStartTime());
            addQuery.setString(5, event.getEndTime());
            addQuery.setString(6, event.getEventName());
            addQuery.setString(7, event.getLocation());
            addQuery.execute();

        } catch (SQLException e) {
            throw new ScheduleException("Failed creating a new schedule item.", e);
        }
    }

    @Override
    public void update(Event event) throws ScheduleException {
        try {
            changeQuery.setInt(6, event.getId());
            changeQuery.setString(1, event.getDayOfWeek());
            changeQuery.setString(2, event.getStartTime());
            changeQuery.setString(3, event.getEndTime());
            changeQuery.setString(4, event.getEventName());
            changeQuery.setString(5, event.getLocation());
            changeQuery.execute();
        } catch (SQLException e) {
            throw new ScheduleException("Failed updating a schedule item.", e);
        }
    }

    @Override
    public void delete(Event event) throws ScheduleException {
        try {
            deleteQuery.setInt(1, event.getId());
            deleteQuery.execute();
        } catch (SQLException e) {
            throw new ScheduleException("Failed deleting a schedule item.", e);
        }
    }

    @Override
    public List<Event> getByEventName(String eventName) throws ScheduleException {
        List<Event> events = new ArrayList<>();
        try {
            getByNameQuery.setString(1, eventName);
            ResultSet rs = getByNameQuery.executeQuery();
            while(rs.next()) {
                events.add(new Event(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            throw new ScheduleException("Failed getting event by name.", e);
        }
        return events;
    }
}
