package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.List;

public interface Dao<T> {

    ArrayList<T> get(int id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);
}

