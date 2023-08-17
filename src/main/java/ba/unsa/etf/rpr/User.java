package ba.unsa.etf.rpr;

public class User {
    private int id;
    private String username, password, salt, firstName, lastName, email;

    public User(int id, String username, String password, String salt, String firstName, String lastName, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    public User() {
    }
}
