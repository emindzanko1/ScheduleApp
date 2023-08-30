package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.ScheduleException;

import java.util.ArrayList;
import java.util.List;

/**
 * Root interface for all DAO classes
 * @author Emin Džanko
 */
public interface Dao<T> {

    ArrayList<T> get(int id) throws ScheduleException;

    List<T> getAll() throws ScheduleException;

    void save(T t) throws ScheduleException;

    void update(T t) throws ScheduleException;

    void delete(T t) throws ScheduleException;
}

