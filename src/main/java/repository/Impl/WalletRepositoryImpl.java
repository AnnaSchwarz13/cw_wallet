package repository.Impl;

import entities.User;
import entities.Wallet;
import repository.UserRepository;
import repository.WalletRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletRepositoryImpl implements WalletRepository {
    static DataSource ds;
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
    public static final String UPDATE_WALLET_BALANCE= """
            UPDATE wallet
            SET balance = ?
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
                long walletId = resultSet.getLong(1);
                double balance = resultSet.getDouble(2);
                int userId= resultSet.getInt(3);
                User walletUser = userRepo.read(userId);
                wallet = new Wallet(walletId,balance,walletUser);
            }

            return wallet;
        }
    }

    public static void updateAmount(double amount,long walletId) throws SQLException {
        try (var statement = ds.getConnection().prepareStatement(UPDATE_WALLET_BALANCE)) {
            statement.setDouble(1, amount);
            statement.setLong(2, walletId);
            ;
        }
    }


}
