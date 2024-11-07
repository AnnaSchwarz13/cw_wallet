package repository.Impl;

import entities.User;
import repository.UserRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {
    DataSource ds;
    private static final String INSERT_SQL = """
            INSERT INTO wallet_user(username,password)
            VALUES (?, ?)
            """;

    private static final String DELETE_BY_ID_SQL = """
            DELETE FROM wallet_user
            WHERE id = ?
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT * FROM wallet_user
            WHERE id = ?
            """;

    @Override
    public User create(User user) throws SQLException {

        try (var statement = ds.getConnection().prepareStatement(INSERT_SQL)) {
          statement.setString(1, user.getUsername());
          statement.setString(2, user.getPassword());
        }
        return user;
    }

    @Override
    public User read(int id) throws SQLException {
        try (var statement = ds.getConnection().prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            User tmpUser = null;
            if (resultSet.next()) {
                Long userId = resultSet.getLong(1);
                String username= resultSet.getString(2);
                String password= resultSet.getString(3);
                tmpUser = new User(userId,username,password);
            }

            return tmpUser;
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (var statement = ds.getConnection().prepareStatement(DELETE_BY_ID_SQL)) {
            statement.setLong(1, id);
            var affectedRows = statement.executeUpdate();
            System.out.println("# of Contacts deleted: " + affectedRows);
        }

    }
}
