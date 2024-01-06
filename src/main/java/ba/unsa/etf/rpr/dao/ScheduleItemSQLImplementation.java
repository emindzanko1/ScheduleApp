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
    private final PreparedStatement  pretragaUpit, dodavanjeUpit, noviIdUpit, izmjenaUpit, brisanjeUpit, sviUpit, poImenuUpit;

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
        pretragaUpit = conn.prepareStatement("SELECT * FROM ScheduleItem WHERE Item_ID=?");
        noviIdUpit = conn.prepareStatement("SELECT MAX(Item_ID)+1 FROM ScheduleItem");
        dodavanjeUpit = conn.prepareStatement("INSERT INTO ScheduleItem VALUES(?,?,?,?,?,?,?)");
        izmjenaUpit = conn.prepareStatement("UPDATE ScheduleItem SET dayOfWeek=?, startTime=?, endTime=?, eventName=?, location=? WHERE Item_ID=?");
        brisanjeUpit = conn.prepareStatement("DELETE FROM ScheduleItem WHERE Item_ID=?");
        sviUpit = conn.prepareStatement("SELECT * FROM ScheduleItem");
        poImenuUpit = conn.prepareStatement("SELECT * FROM ScheduleItem WHERE eventName=?");
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
            pretragaUpit.setString(1, String.valueOf(id));
            ResultSet rs = pretragaUpit.executeQuery();
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
            ResultSet rs = sviUpit.executeQuery();
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
            ResultSet rs = noviIdUpit.executeQuery();
            if(rs.next())
                event.setId(rs.getInt(1));
            else
                event.setId(1);

            dodavanjeUpit.setInt(1, event.getId());
            dodavanjeUpit.setInt(2, event.getScheduleId());
            dodavanjeUpit.setString(3, event.getDayOfWeek());
            dodavanjeUpit.setString(4, event.getStartTime());
            dodavanjeUpit.setString(5, event.getEndTime());
            dodavanjeUpit.setString(6, event.getEventName());
            dodavanjeUpit.setString(7, event.getLocation());
            dodavanjeUpit.execute();

        } catch (SQLException e) {
            throw new ScheduleException("Failed creating a new schedule item.", e);
        }
    }

    @Override
    public void update(Event event) throws ScheduleException {
        try {
            izmjenaUpit.setInt(6, event.getId());
            izmjenaUpit.setString(1, event.getDayOfWeek());
            izmjenaUpit.setString(2, event.getStartTime());
            izmjenaUpit.setString(3, event.getEndTime());
            izmjenaUpit.setString(4, event.getEventName());
            izmjenaUpit.setString(5, event.getLocation());
            izmjenaUpit.execute();
        } catch (SQLException e) {
            throw new ScheduleException("Failed updating a schedule item.", e);
        }
    }

    @Override
    public void delete(Event event) throws ScheduleException {
        try {
            brisanjeUpit.setInt(1, event.getId());
            brisanjeUpit.execute();
        } catch (SQLException e) {
            throw new ScheduleException("Failed deleting a schedule item.", e);
        }
    }

    @Override
    public List<Event> getByEventName(String eventName) throws ScheduleException {
        List<Event> events = new ArrayList<>();
        try {
            poImenuUpit.setString(1, eventName);
            ResultSet rs = poImenuUpit.executeQuery();
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
