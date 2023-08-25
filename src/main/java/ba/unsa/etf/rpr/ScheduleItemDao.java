package ba.unsa.etf.rpr;

import java.util.List;

public interface ScheduleItemDao extends Dao<ScheduleItem> {
    List<ScheduleItem> getByEventName(String eventName);
}