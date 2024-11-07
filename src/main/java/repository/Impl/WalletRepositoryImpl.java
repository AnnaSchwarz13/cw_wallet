package repository.Impl;

import entities.User;
import entities.Wallet;
import repository.UserRepository;
import repository.WalletRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletRepositoryImpl implements WalletRepository {
    DataSource ds;
    UserRepository userRepo;
    private static final String INSERT_SQL = """
            INSERT INTO Wallet(balance , user_id)
            VALUES (?, ?)
            """;

    private static final String DELETE_BY_ID_SQL = """
            DELETE FROM wallet
            WHERE id = ?
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT * FROM wallet
            WHERE id = ?
            """;

    @Override
    public void delete(int id) throws SQLException {
        try (var statement = ds.getConnection().prepareStatement(DELETE_BY_ID_SQL)) {
            statement.setLong(1, id);
            var affectedRows = statement.executeUpdate();
            System.out.println("# of Contacts deleted: " + affectedRows);
        }
    }

    @Override
    public Wallet create(Wallet wallet) throws SQLException {
        try (var statement = ds.getConnection().prepareStatement(INSERT_SQL)) {
            statement.setDouble(1, wallet.getBalance());
            statement.setLong(2, wallet.getUser().getId());
        }
        return wallet;
    }

    @Override
    public Wallet read(int id) throws SQLException {
        try (var statement = ds.getConnection().prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            Wallet wallet = null;
            if (resultSet.next()) {
                Long walletId = resultSet.getLong(1);
                Double balance = resultSet.getDouble(2);
                int userId= resultSet.getInt(3);
                User walletUser = userRepo.read(userId);
                wallet = new Wallet(walletId,balance,walletUser);
            }

            return wallet;
        }
    }

}
