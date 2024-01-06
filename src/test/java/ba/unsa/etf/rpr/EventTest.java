package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.ScheduleItemDao;
import ba.unsa.etf.rpr.dao.ScheduleItemSQLImplementation;
import ba.unsa.etf.rpr.domain.Event;
import ba.unsa.etf.rpr.exceptions.ScheduleException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * JUnit tests for ScheduleItem
 * @author Emin Džanko
 */
public class EventTest {
    private ScheduleItemDao scheduleItemDaoMock;
    private ScheduleItemDao scheduleItemDao;

    @BeforeEach
    public void setUp() throws SQLException {
        scheduleItemDaoMock = mock(ScheduleItemDao.class);
        scheduleItemDao = ScheduleItemSQLImplementation.getInstance();

    }

    /*@Test
    public void getScheduleItemByIdTest() throws ScheduleException {
        int id = 1;
        ArrayList<Event> expectedEvents = new ArrayList<>();
        expectedEvents.add(new Event(28, 63, "Friday", "08:00", "10:00", "Sortiraj", "agadsgdag"));

        Mockito.when(scheduleItemDaoMock.get(id)).thenReturn(expectedEvents);

        List<Event> actualEvents = scheduleItemDaoMock.get(id);

        Assertions.assertEquals(expectedEvents, actualEvents);
        Assertions.assertEquals(expectedEvents.size(), actualEvents.size());
        Assertions.assertEquals(expectedEvents.get(0).getId(), actualEvents.get(0).getId());
    }

    @Test
    public void getAllSchedulesTest() {
        List<Event> schedulesItems = null;
        try {
            schedulesItems = scheduleItemDao.getAll();
        } catch (ScheduleException e) {
            e.printStackTrace();
        }
        assert schedulesItems != null;
        Assertions.assertEquals(schedulesItems.size(), 31);
    }

    @Test
    public void getScheduleByScheduleNameTest() throws ScheduleException {
        List<Event> expectedEvents = new ArrayList<>();
        expectedEvents.add(new Event(28, 63, "Friday", "08:00", "10:00", "Sortiraj", "agadsgdag"));

        Mockito.when(scheduleItemDaoMock.getByEventName("Sortiraj")).thenReturn(expectedEvents);

        List<Event> actualEvents = scheduleItemDaoMock.getByEventName("Sortiraj");

        Assertions.assertEquals(expectedEvents, actualEvents);
        Assertions.assertEquals(expectedEvents.size(), actualEvents.size());
        Assertions.assertEquals(expectedEvents.get(0).getId(), actualEvents.get(0).getId());
    }*/
}
