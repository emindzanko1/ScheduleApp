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
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ScheduleItemTest {
    private ScheduleItemDao scheduleItemDaoMock;
    private ScheduleItemDao scheduleItemDao;


    @BeforeEach
    public void setUp() throws SQLException {
        scheduleItemDaoMock = mock(ScheduleItemDao.class);
        scheduleItemDao = ScheduleItemSQLImplementation.getInstance();

    }

    @Test
    public void getScheduleItemByIdTest() throws ScheduleException {
        int id = 1;
        ArrayList<ScheduleItem> expectedScheduleItems = new ArrayList<>();
        expectedScheduleItems.add(new ScheduleItem(28, 63, "Friday", "08:00", "10:00", "Sortiraj", "agadsgdag"));

        Mockito.when(scheduleItemDaoMock.get(id)).thenReturn(expectedScheduleItems);

        List<ScheduleItem> actualScheduleItems = scheduleItemDaoMock.get(id);

        assertEquals(expectedScheduleItems, actualScheduleItems);
        assertEquals(expectedScheduleItems.size(), actualScheduleItems.size());
        assertEquals(expectedScheduleItems.get(0).getId(), actualScheduleItems.get(0).getId());
    }

    @Test
    public void getAllSchedulesTest() throws ScheduleException {
        List<ScheduleItem> schedulesItems = null;
        try {
            schedulesItems = scheduleItemDao.getAll();
        } catch (ScheduleException e) {
            e.printStackTrace();
        }
        assertEquals(schedulesItems.size(), 31);
    }

    @Test
    public void getScheduleByScheduleNameTest() throws ScheduleException {
        List<ScheduleItem> expectedScheduleItems = new ArrayList<>();
        expectedScheduleItems.add(new ScheduleItem(28, 63, "Friday", "08:00", "10:00", "Sortiraj", "agadsgdag"));

        Mockito.when(scheduleItemDaoMock.getByEventName("Sortiraj")).thenReturn(expectedScheduleItems);

        List<ScheduleItem> actualScheduleItems = scheduleItemDaoMock.getByEventName("Sortiraj");

        assertEquals(expectedScheduleItems, actualScheduleItems);
        assertEquals(expectedScheduleItems.size(), actualScheduleItems.size());
        assertEquals(expectedScheduleItems.get(0).getId(), actualScheduleItems.get(0).getId());
    }
}
