package repository;

import entities.User;

import java.sql.SQLException;

public interface UserRepository {
    User create (User user) throws SQLException;
    User read(int id) throws SQLException;
}
