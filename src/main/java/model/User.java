package model;

public abstract class User {

    protected int userId;
    protected String nama;
    protected String username;
    protected String password;
    protected String role;

    public User(int userId,
                String nama,
                String username,
                String password,
                String role) {

        this.userId = userId;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean login(String username,
                         String password) {

        return this.username.equals(username)
                && this.password.equals(password);
    }

    public int getUserId() {
        return userId;
    }

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public abstract String dashboardName();
}