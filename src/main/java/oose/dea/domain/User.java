package oose.dea.domain;

public class User {

    private int userId;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    public User(int userId, String firstname, String lastname, String email, String password) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

}
