package ba.unsa.etf.rpr.dao;

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
public class ScheduleSQLImplementation implements ScheduleDao {

    private static ScheduleSQLImplementation instance = null;

    private Connection conn;
    private final PreparedStatement searchQuery, addQuery, newIdQuery, changeQuery, deleteQuery, allQuery, getByNameQuery;

    private ScheduleSQLImplementation() throws SQLException {
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
        searchQuery = conn.prepareStatement("SELECT * FROM Schedule WHERE Schedule_ID=?");
        newIdQuery = conn.prepareStatement("SELECT MAX(Schedule_ID)+1 FROM Schedule");
        addQuery = conn.prepareStatement("INSERT INTO Schedule VALUES(?,?,?)");
        allQuery = conn.prepareStatement("SELECT * FROM Schedule");
        changeQuery = conn.prepareStatement("UPDATE Schedule SET ScheduleName=? WHERE Schedule_ID=?");
        deleteQuery = conn.prepareStatement("DELETE FROM Schedule WHERE Schedule_ID=?");
        getByNameQuery = conn.prepareStatement("SELECT * FROM Schedule WHERE ScheduleName=?");

    }

    public static ScheduleSQLImplementation getInstance() throws SQLException {
        if (instance == null) instance = new ScheduleSQLImplementation();
        return instance;
    }

    public static void removeInstance() throws SQLException {
        if (instance == null) return;
        instance.conn.close();
        instance = null;
    }

    @Override
    public Schedule get(int id) throws ScheduleException {
        Schedule schedule = new Schedule();
        try {
            searchQuery.setString(1, String.valueOf(id));
            ResultSet rs = searchQuery.executeQuery();
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            throw new ScheduleException("Failed getting a schedule by id.", e);
        }
        return schedule;
    }

    @Override
    public List<Schedule> getAll() throws ScheduleException {
        List<Schedule> schedules = new ArrayList<>();
        try {
            ResultSet rs = allQuery.executeQuery();
            while(rs.next()) {
                schedules.add(new Schedule(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }
            System.out.println("Connection successful!");
        }
        catch (SQLException e) {
            throw new ScheduleException("Failed getting all schedules.", e);
        }
        return schedules;
    }

    @Override
    public void save(Schedule schedule) throws ScheduleException {
        try {
            ResultSet rs = newIdQuery.executeQuery();
            if(rs.next())
                schedule.setId(rs.getInt(1));
            else
                schedule.setId(1);
            addQuery.setInt(1, schedule.getId());
            addQuery.setInt(2, schedule.getUserId());
            System.out.println(schedule.getUserId());
            addQuery.setString(3, schedule.getScheduleName());

            addQuery.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

            throw new ScheduleException("Failed creating a new schedule.", e);
        }
    }

    @Override
    public void update(Schedule schedule) throws ScheduleException {
        try {
            changeQuery.setInt(2, schedule.getId());
            changeQuery.setString(1, schedule.getScheduleName());
            changeQuery.execute();
        } catch (SQLException e) {
            throw new ScheduleException("Failed updating a schedule.", e);
        }
    }

    @Override
    public void delete(Schedule schedule) throws ScheduleException {
        try {
            deleteQuery.setInt(1, schedule.getId());
            deleteQuery.execute();
        } catch (SQLException e) {
            throw new ScheduleException("Failed deleting a schedule.", e);
        }
    }

    @Override
    public Schedule getByScheduleName(String scheduleName) throws ScheduleException {
        Schedule schedule = new Schedule();
        try {
            getByNameQuery.setString(1, scheduleName);
            ResultSet rs = getByNameQuery.executeQuery();
            while(rs.next()) {
                schedule = new Schedule(rs.getInt(1), rs.getInt(2), rs.getString(3));
            }
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            throw new ScheduleException("Failed getting a schedule by name.", e);
        }
        return schedule;
    }
}
