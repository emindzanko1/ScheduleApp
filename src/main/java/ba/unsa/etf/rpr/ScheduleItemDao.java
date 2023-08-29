package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.exceptions.ScheduleException;

import java.util.List;

public interface ScheduleItemDao extends Dao<ScheduleItem> {
    List<ScheduleItem> getByEventName(String eventName) throws ScheduleException;
}