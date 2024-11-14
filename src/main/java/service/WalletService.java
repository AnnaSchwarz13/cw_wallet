package service;

import entities.Wallet;

import java.sql.SQLException;

public interface WalletService {

    void withdraw(double amount, Wallet wallet) throws SQLException;

    void deposit(double amount, Wallet wallet) throws SQLException;

    double displayRecentBalance(Wallet wallet);

    Wallet getWalletById(long id) throws SQLException;
}
