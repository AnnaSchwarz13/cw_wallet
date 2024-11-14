package service;

import entities.User;
import exceptions.UserException;

import java.sql.SQLException;

public interface UserService {
    void userSignUp(User user) throws SQLException, UserException;

    void userLogin(String username, String password) throws UserException;

    void userLogout() throws UserException;
}
