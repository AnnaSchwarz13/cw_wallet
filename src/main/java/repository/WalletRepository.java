package repository;

import entities.Wallet;

import java.sql.SQLException;

public interface WalletRepository {
    Wallet create(Wallet wallet) throws SQLException;
    Wallet read(int id) throws SQLException;
     void delete(int id) throws SQLException;
}
