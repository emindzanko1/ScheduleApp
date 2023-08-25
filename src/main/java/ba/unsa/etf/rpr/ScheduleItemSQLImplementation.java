package ba.unsa.etf.rpr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleItemSQLImplementation implements ScheduleItemDao{
    private static ScheduleItemSQLImplementation instance = null;

    private Connection conn;
    private PreparedStatement pretragaUpit, dodavanjeUpit, noviIdUpit, izmjenaUpit, brisanjeUpit, sviUpit, poImenuUpit;

    private ScheduleItemSQLImplementation() throws SQLException {
        String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_RPR Projekat";
        String username = "freedb_edzanko1";
        String password = System.getenv("MYSQL_PASS");
        conn = DriverManager.getConnection(url, username, password);
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
    public ArrayList<ScheduleItem> get(int id) {
        return null;
    }

    @Override
    public List<ScheduleItem> getAll() {
        return null;
    }

    @Override
    public void save(ScheduleItem scheduleItem) {

    }

    @Override
    public void update(ScheduleItem scheduleItem) {

    }

    @Override
    public void delete(ScheduleItem scheduleItem) {

    }

    @Override
    public List<ScheduleItem> getByEventName(String eventName) {
        return null;
    }
}
