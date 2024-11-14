package repository.Impl;

import entities.User;
import entities.Wallet;
import repository.UserRepository;
import repository.WalletRepository;
import repository.Datasource;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletRepositoryImpl implements WalletRepository {
    TransactionRepositoryImpl transactionRepository = new TransactionRepositoryImpl();
    UserRepository userRepo = new UserRepositoryImpl();
    private static final String INSERT_SQL = """
            INSERT INTO Wallet(balance , user_id)
            VALUES (?, ?)
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT * FROM wallet
            WHERE id = ?
            """;

    public static final String UPDATE_WALLET_BALANCE = """
            UPDATE wallet
            SET balance = ?
            WHERE id = ?
            """;


    @Override
    public Wallet create(Wallet wallet) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(INSERT_SQL)) {
            statement.setDouble(1, wallet.getBalance());
            statement.setLong(2, wallet.getUser().getId());
            statement.execute();
        }
        return wallet;
    }

    @Override
    public Wallet read(long id) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            Wallet wallet = null;
            if (resultSet.next()) {
                long walletId = resultSet.getLong(1);
                double balance = resultSet.getDouble(2);
                int userId = resultSet.getInt(3);
                User walletUser = userRepo.read(userId);
                wallet = new Wallet(walletId, balance, walletUser);
                wallet.setTransactions(transactionRepository.allTransactions(wallet));
            }

            return wallet;
        }
    }
@Override
    public void updateAmount(double amount, long walletId) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(UPDATE_WALLET_BALANCE)) {
            statement.setDouble(1, amount);
            statement.setLong(2, walletId);
            statement.execute();

        }
    }


}
