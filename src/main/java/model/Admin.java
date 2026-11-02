package model;

public class Admin extends User {
    @Override
    public String dashboardName() {
        return "Admin Dashboard";
    }

    public Admin(int userId,
            String nama,
            String username,
            String password) {

        super(
                userId,
                nama,
                username,
                password,
                "Admin");
    }
}