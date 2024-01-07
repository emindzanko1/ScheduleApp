package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Schedule;
import ba.unsa.etf.rpr.exceptions.ScheduleException;

/**
 * Dao interface for Schedule domain bean
 * @author Emin Džanko
 */
public interface ScheduleDao extends Dao<Schedule>{
    Schedule getByScheduleName(String scheduleName) throws ScheduleException;
    int getNumberOfSchedules(int userId) throws ScheduleException;
}
