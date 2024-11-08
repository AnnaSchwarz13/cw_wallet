package repository.Impl;

import entities.User;
import repository.UserRepository;

import repository.Datasource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

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
    private static final String READ_ALL_SQL = """
            SELECT * FROM wallet_user
            """;
    private static final String FIND_WALLET = """
            SELECT id FROM wallet
            WHERE user_id = ?
            """;

    @Override
    public User create(User user) throws SQLException {

        try (var statement = Datasource.getConnection().prepareStatement(INSERT_SQL)) {
          statement.setString(1, user.getUsername());
          statement.setString(2, user.getPassword());
        }
        return user;
    }

    @Override
    public User read(int id) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(FIND_BY_ID_SQL)) {
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
        try (var statement = Datasource.getConnection().prepareStatement(DELETE_BY_ID_SQL)) {
            statement.setLong(1, id);
            var affectedRows = statement.executeUpdate();
            System.out.println("# of Contacts deleted: " + affectedRows);
        }

    }

    public static List<User> all() {
        try (var statement = Datasource.getConnection().prepareStatement(READ_ALL_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new LinkedList<>();
            while (resultSet.next()) {
                long userId = resultSet.getLong(1);
                String username = resultSet.getString(2);
                String password = resultSet.getString(3);
                User user = new User(userId, username, password);
                users.add(user);
            }

            return new ArrayList<>(users);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static long findWalletIdByUserId(long userId)throws SQLException {

        try (var statement = Datasource.getConnection().prepareStatement(FIND_WALLET)) {
            ResultSet resultSet = statement.executeQuery();
            long walletId=0;
            if (resultSet.next()) {
                walletId = resultSet.getLong(1);
            }

            return walletId;
        }
    }
}
