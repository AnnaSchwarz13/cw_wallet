package repository;

import entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    User create (User user) throws SQLException;
    User read(long id) throws SQLException;
    long findByUsername(String username) throws SQLException;
    long findWalletIdByUserId(long userId)throws SQLException;
    List<User> all();
}
