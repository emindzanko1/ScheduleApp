package ba.unsa.etf.rpr;

import java.util.List;

public interface UserDao extends Dao<User> {
    List<User> getByUsername(String username);
}