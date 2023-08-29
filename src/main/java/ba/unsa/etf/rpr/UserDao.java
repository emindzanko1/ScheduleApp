package ba.unsa.etf.rpr;

import java.util.List;

public interface UserDao extends Dao<User> {
    User getByUsername(String username);
}