package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.ScheduleDao;
import ba.unsa.etf.rpr.dao.ScheduleItemDao;
import ba.unsa.etf.rpr.dao.ScheduleItemSQLImplementation;
import ba.unsa.etf.rpr.dao.ScheduleSQLImplementation;
import ba.unsa.etf.rpr.domain.Schedule;
import ba.unsa.etf.rpr.domain.ScheduleItem;
import ba.unsa.etf.rpr.exceptions.ScheduleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertEquals;

public class ScheduleItemTest {
    private ScheduleItemDao scheduleItemDao;

    @BeforeEach
    public void setUp() {
        try {
            scheduleItemDao = ScheduleItemSQLImplementation.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getScheduleItemByIdTest() {
        int id = 1;
        List<ScheduleItem> scheduleItems = null;
        try {
            scheduleItems = scheduleItemDao.get(id);
        } catch (ScheduleException e) {
            e.printStackTrace();
        }
        assertNotNull(scheduleItems);
        assertFalse(scheduleItems.isEmpty());
        assertEquals(1, scheduleItems.size());

        ScheduleItem foundScheduleItem = scheduleItems.get(0);
        assertEquals(foundScheduleItem.getId(), 1);
    }

    @Test
    public void getAllSchedulesTest() {
        List<ScheduleItem> schedulesItems = null;
        try {
            schedulesItems = scheduleItemDao.getAll();
        } catch (ScheduleException e) {
            e.printStackTrace();
        }
        assertEquals(schedulesItems.size(), 31);
    }

    @Test
    public void getScheduleByScheduleNameTest() {
        int id = 7;
        List<ScheduleItem> schedulesItems = null;
        try {
            schedulesItems = scheduleItemDao.get(id);
        } catch (ScheduleException e) {
            e.printStackTrace();
        }
        ScheduleItem foundScheduleItem = schedulesItems.get(0);
        assertEquals(foundScheduleItem.getEventName(), "Event");
    }
}
