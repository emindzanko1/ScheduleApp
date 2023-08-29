package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.exceptions.ScheduleException;

import java.util.List;

public interface ScheduleDao extends Dao<Schedule>{
    List<Schedule> getByScheduleName(String scheduleName) throws ScheduleException;
}
