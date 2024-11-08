package repository.Impl;

import entities.Transaction;
import entities.User;
import entities.Wallet;
import entities.enums.TransactionType;
import repository.TransactionRepository;
import repository.Datasource;

import java.lang.annotation.Target;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository {

    WalletRepositoryImpl walletRepositoryImpl = new WalletRepositoryImpl();
    static TransactionRepositoryImpl transactionRepositoryImpl = new TransactionRepositoryImpl();
    private static final String INSERT_SQL = """
            INSERT INTO transactions(amount,transaction_type,wallet_id,transaction_date)
            VALUES (?, ? , ? ,?)
            """;

    private static final String DELETE_BY_ID_SQL = """
            DELETE FROM transactions
            WHERE id = ?
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT * FROM transactions
            WHERE id = ?
            """;
    private static final String FIND_ALL_TRANSACTION= """
            SELECT * FROM transactions
            WHERE wallet_id = ?
            """;

    @Override
    public void delete(int id) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(DELETE_BY_ID_SQL)) {
            statement.setLong(1, id);
            var affectedRows = statement.executeUpdate();
            System.out.println("# of Contacts deleted: " + affectedRows);
        }
    }

    @Override
    public Transaction read(int id) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            Transaction transaction = null;
            if (resultSet.next()) {
                long transactionId = resultSet.getLong(1);
                double amount = resultSet.getDouble(2);
                String transactionType = resultSet.getString(3);
                int walletId = resultSet.getInt(4);
                Date transactionDate = resultSet.getDate(5);
                Wallet wallet = walletRepositoryImpl.read(walletId);
                transaction = new Transaction(transactionId,amount,TransactionType.valueOf(transactionType),transactionDate,wallet);
            }
            return transaction;

        }
    }

    @Override
    public Transaction create(Transaction transaction) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(INSERT_SQL)) {
            statement.setDouble(1, transaction.getAmount());
            statement.setString(2, String.valueOf(transaction.getType()));
            statement.setLong(3, transaction.getWallet().getId());
            statement.setDate(4, (Date) transaction.getDate());
        }
        return transaction;
    }

    public static List<Transaction> allTransactions(Wallet wallet) {
        try (var statement = Datasource.getConnection().prepareStatement(FIND_ALL_TRANSACTION)) {
            statement.setDouble(1, wallet.getId());
            ResultSet resultSet = statement.executeQuery();
            List<Transaction> transactions = new LinkedList<>();
            while (resultSet.next()) {
                Transaction transaction = transactionRepositoryImpl.read(resultSet.getInt(1));
                transactions.add(transaction);
            }

            return new ArrayList<>(transactions);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
