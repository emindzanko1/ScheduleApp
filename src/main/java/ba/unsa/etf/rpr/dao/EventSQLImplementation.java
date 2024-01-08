package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Event;
import ba.unsa.etf.rpr.domain.Schedule;
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
public class EventSQLImplementation implements EventDao {
    private static EventSQLImplementation instance = null;

    private Connection conn;
    private final PreparedStatement searchQuery, addQuery, newIdQuery, changeQuery, deleteQuery, allQuery, getByNameQuery, getByScheduleIdQuery;

    private EventSQLImplementation() throws SQLException {
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
        searchQuery = conn.prepareStatement("SELECT * FROM Event WHERE event_id=?");
        newIdQuery = conn.prepareStatement("SELECT MAX(event_id)+1 FROM Event");
        addQuery = conn.prepareStatement("INSERT INTO Event VALUES(?,?,?,?,?,?)");
        changeQuery = conn.prepareStatement("UPDATE Event SET day_of_week=?, start_time=?, event_name=?, location=? WHERE event_id=?");
        deleteQuery = conn.prepareStatement("DELETE FROM Event WHERE event_id=?");
        allQuery = conn.prepareStatement("SELECT * FROM Event");
        getByNameQuery = conn.prepareStatement("SELECT * FROM Event WHERE eventame=?");
        getByScheduleIdQuery = conn.prepareStatement("SELECT * FROM Event WHERE schedule_id=?");
    }

    public static EventSQLImplementation getInstance() throws SQLException {
        if (instance == null) instance = new EventSQLImplementation();
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
                events.add(new Event(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            throw new ScheduleException("Failed getting all schedule events.", e);
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
            addQuery.setString(3, event.getEventName());
            addQuery.setString(4, event.getDayOfWeek());
            addQuery.setString(5, event.getStartTime());
            addQuery.setString(6, event.getLocation());
            addQuery.execute();

        } catch (SQLException e) {
            throw new ScheduleException("Failed creating a new schedule event.", e);
        }
    }

    @Override
    public void update(Event event) throws ScheduleException {
        try {
            changeQuery.setInt(5, event.getId());
            changeQuery.setString(1, event.getDayOfWeek());
            changeQuery.setString(2, event.getStartTime());
            changeQuery.setString(3, event.getEventName());
            changeQuery.setString(4, event.getLocation());
            changeQuery.execute();
        } catch (SQLException e) {
            throw new ScheduleException("Failed updating a schedule event.", e);
        }
    }

    @Override
    public void delete(Event event) throws ScheduleException {
        try {
            deleteQuery.setInt(1, event.getId());
            deleteQuery.execute();
        } catch (SQLException e) {
            throw new ScheduleException("Failed deleting a schedule event.", e);
        }
    }

    @Override
    public List<Event> getByEventName(String eventName) throws ScheduleException {
        List<Event> events = new ArrayList<>();
        try {
            getByNameQuery.setString(1, eventName);
            ResultSet rs = getByNameQuery.executeQuery();
            while(rs.next()) {
                events.add(new Event(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            throw new ScheduleException("Failed getting event by name.", e);
        }
        return events;
    }

    @Override
    public List<Event> getByScheduleId(int scheduleId) throws ScheduleException {
        List<Event> events = new ArrayList<>();
        try {
            getByScheduleIdQuery.setInt(1, scheduleId);
            ResultSet rs = getByScheduleIdQuery.executeQuery();
            while (rs.next()) {
                events.add(new Event(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            throw new ScheduleException("Failed getting events by schedule ID.", e);
        }
        return events;
    }
}
