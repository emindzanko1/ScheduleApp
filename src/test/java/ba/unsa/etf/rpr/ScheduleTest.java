package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.ScheduleDao;
import ba.unsa.etf.rpr.dao.ScheduleSQLImplementation;
import ba.unsa.etf.rpr.dao.UserDao;
import ba.unsa.etf.rpr.dao.UserSQLImplementation;
import ba.unsa.etf.rpr.domain.Schedule;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.ScheduleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertEquals;

public class ScheduleTest {

    private ScheduleDao scheduleDao;

    @BeforeEach
    public void setUp() {
        try {
            scheduleDao = ScheduleSQLImplementation.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getScheduleByIdTest() {
        int id = 10;
        List<Schedule> schedules = null;
        try {
            schedules = scheduleDao.get(id);
        } catch (ScheduleException e) {
            e.printStackTrace();
        }
        assertNotNull(schedules);
        assertFalse(schedules.isEmpty());
        assertEquals(1, schedules.size());

        Schedule foundSchedule = schedules.get(0);
        assertEquals(foundSchedule.getId(), 10);
    }
}
