package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Event;
import ba.unsa.etf.rpr.exceptions.ScheduleException;

import java.util.List;

/**
 * Dao interface for ScheduleItem domain bean
 * @author Emin Džanko
 */
public interface ScheduleItemDao extends Dao<Event> {
    List<Event> getByEventName(String eventName) throws ScheduleException;
}