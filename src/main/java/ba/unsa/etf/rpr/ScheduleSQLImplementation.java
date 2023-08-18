package ba.unsa.etf.rpr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleSQLImplementation implements ScheduleDao {

    private static ScheduleSQLImplementation instance = null;

    private Connection conn;
    private PreparedStatement pretragaUpit, dodavanjeUpit, noviIdUpit, izmjenaUpit, brisanjeUpit, sviUpit;

    private ScheduleSQLImplementation() throws SQLException {
        String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_RPR Projekat";
        String username = "freedb_edzanko1";
        String password = System.getenv("MYSQL_PASS");
        conn = DriverManager.getConnection(url, username, password);
    }

    @Override
    public ArrayList<Schedule> get(int id) {
        return null;
    }

    @Override
    public List<Schedule> getAll() {
        return null;
    }

    @Override
    public void save(Schedule schedule) {

    }

    @Override
    public void update(Schedule schedule) {

    }

    @Override
    public void delete(Schedule schedule) {

    }

    @Override
    public List<Schedule> getByScheduleName(String scheduleName) {
        return null;
    }
}
