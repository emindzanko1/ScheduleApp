package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.ScheduleItem;
import ba.unsa.etf.rpr.exceptions.ScheduleException;

import java.util.List;

/**
 * Dao interface for ScheduleItem domain bean
 * @author Emin Džanko
 */
public interface ScheduleItemDao extends Dao<ScheduleItem> {
    List<ScheduleItem> getByEventName(String eventName) throws ScheduleException;
}