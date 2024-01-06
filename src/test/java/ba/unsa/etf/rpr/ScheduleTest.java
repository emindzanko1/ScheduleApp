package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.ScheduleDao;
import ba.unsa.etf.rpr.dao.ScheduleSQLImplementation;
import ba.unsa.etf.rpr.domain.Schedule;
import ba.unsa.etf.rpr.exceptions.ScheduleException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * JUnit tests for Schedule
 * @author Emin Džanko
 */

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

    /*@Test
    public void getScheduleByIdTest() {
        int id = 10;
        List<Schedule> schedules = null;
        try {
            schedules = scheduleDao.get(id);
        } catch (ScheduleException e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(schedules);
        Assertions.assertFalse(schedules.isEmpty());
        Assertions.assertEquals(1, schedules.size());

        Schedule foundSchedule = schedules.get(0);
        Assertions.assertEquals(foundSchedule.getId(), 10);
    }

    @Test
    public void getAllSchedulesTest() {
        List<Schedule> schedules = null;
        try {
            schedules = scheduleDao.getAll();
        } catch (ScheduleException e) {
            e.printStackTrace();
        }
        assert schedules != null;
        Assertions.assertEquals(schedules.size(), 63);
    }

    @Test
    public void getScheduleByScheduleNameTest() {
        int id = 41;
        List<Schedule> schedules = null;
        try {
            schedules = scheduleDao.get(id);
        } catch (ScheduleException e) {
            e.printStackTrace();
        }
        assert schedules != null;
        Schedule foundSchedule = schedules.get(0);
        Assertions.assertEquals(foundSchedule.getScheduleName(), "Emin");

    }*/
}
