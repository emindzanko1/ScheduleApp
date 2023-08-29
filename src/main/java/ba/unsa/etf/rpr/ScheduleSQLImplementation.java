package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.exceptions.ScheduleException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleSQLImplementation implements ScheduleDao {

    private static ScheduleSQLImplementation instance = null;

    private final Connection conn;
    private final PreparedStatement pretragaUpit, dodavanjeUpit, noviIdUpit, izmjenaUpit, brisanjeUpit, sviUpit, poImenuUpit;

    private ScheduleSQLImplementation() throws SQLException {
        String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_RPR Projekat";
        String username = "freedb_edzanko1";
        String password = System.getenv("MYSQL_PASS");
        conn = DriverManager.getConnection(url, username, password);
        pretragaUpit = conn.prepareStatement("SELECT * FROM Schedule WHERE Schedule_ID=?");
        noviIdUpit = conn.prepareStatement("SELECT MAX(Schedule_ID)+1 FROM Schedule");
        dodavanjeUpit = conn.prepareStatement("INSERT INTO Schedule VALUES(?,?,?)");
        sviUpit = conn.prepareStatement("SELECT * FROM Schedule");
        izmjenaUpit = conn.prepareStatement("UPDATE Schedule SET ScheduleName=? WHERE Schedule_ID=?");
        brisanjeUpit = conn.prepareStatement("DELETE FROM Schedule WHERE Schedule_ID=?");
        poImenuUpit = conn.prepareStatement("SELECT * FROM Schedule WHERE ScheduleName=?");

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
    public ArrayList<Schedule> get(int id) throws ScheduleException {
        ArrayList<Schedule> schedules = new ArrayList<>();
        try {
            pretragaUpit.setString(1, String.valueOf(id));
            ResultSet rs = pretragaUpit.executeQuery();
            while(rs.next()) {
                schedules.add(new Schedule(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            throw new ScheduleException("Failed getting a schedule by id.", e);
        }
        return schedules;
    }

    @Override
    public List<Schedule> getAll() throws ScheduleException {
        List<Schedule> schedules = new ArrayList<>();
        try {
            ResultSet rs = sviUpit.executeQuery();
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
            ResultSet rs = noviIdUpit.executeQuery();
            if(rs.next())
                schedule.setId(rs.getInt(1));
            else
                schedule.setId(1);

            dodavanjeUpit.setInt(1, schedule.getId());
            dodavanjeUpit.setInt(2, schedule.getUserId());
            dodavanjeUpit.setString(3, schedule.getScheduleName());

            dodavanjeUpit.execute();

        } catch (SQLException e) {
            throw new ScheduleException("Failed creating a new schedule.", e);
        }
    }

    @Override
    public void update(Schedule schedule) throws ScheduleException {
        try {
            izmjenaUpit.setInt(2, schedule.getId());
            izmjenaUpit.setString(1, schedule.getScheduleName());
            izmjenaUpit.execute();
        } catch (SQLException e) {
            throw new ScheduleException("Failed updating a schedule.", e);
        }
    }

    @Override
    public void delete(Schedule schedule) throws ScheduleException {
        try {
            brisanjeUpit.setInt(1, schedule.getId());
            brisanjeUpit.execute();
        } catch (SQLException e) {
            throw new ScheduleException("Failed deleting a schedule.", e);
        }
    }

    @Override
    public List<Schedule> getByScheduleName(String scheduleName) throws ScheduleException {
        List<Schedule> schedules = new ArrayList<>();
        try {
            poImenuUpit.setString(1, scheduleName);
            ResultSet rs = poImenuUpit.executeQuery();
            while(rs.next()) {
                schedules.add(new Schedule(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            throw new ScheduleException("Failed getting a schedule by name.", e);
        }
        return schedules;
    }
}
