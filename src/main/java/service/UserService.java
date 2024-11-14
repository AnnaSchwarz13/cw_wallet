package service;

import java.sql.SQLException;

public interface UserService {
    void userSignUp() throws SQLException;

    void userLogin(String username, String password);

    void userLogout();
}
