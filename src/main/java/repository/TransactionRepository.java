package repository;

import entities.Transaction;

import java.sql.SQLException;

public interface TransactionRepository {
    Transaction create (Transaction transaction) throws SQLException;
    Transaction read(int id) throws SQLException;
    void delete(int id) throws SQLException;
}
