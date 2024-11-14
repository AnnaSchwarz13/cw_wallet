package repository;

import entities.Transaction;
import entities.Wallet;

import java.sql.SQLException;
import java.util.List;

public interface TransactionRepository {
    Transaction create (Transaction transaction) throws SQLException;
    Transaction read(int id) throws SQLException;
    List<Transaction> allTransactions(Wallet wallet);
}
