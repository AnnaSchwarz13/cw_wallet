package repository;

import entities.Wallet;

import java.sql.SQLException;

public interface WalletRepository {
    Wallet create(Wallet wallet) throws SQLException;
    Wallet read(long id) throws SQLException;
    void updateAmount(double amount, long walletId) throws SQLException;
}
