package service.Impl;

import entities.Transaction;
import entities.Wallet;
import entities.enums.TransactionType;
import repository.Impl.TransactionRepositoryImpl;
import repository.Impl.WalletRepositoryImpl;
import service.WalletService;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class WalletServiceImpl implements WalletService {
    static TransactionRepositoryImpl transactionRepository = new TransactionRepositoryImpl();
    static WalletRepositoryImpl walletRepository = new WalletRepositoryImpl();

    @Override
    public void withdraw(double amount, Wallet wallet) throws SQLException {
        if (wallet.getBalance() >= amount) {
            wallet.setBalance(wallet.getBalance() - amount);
            Transaction transaction = new Transaction(
                    amount, TransactionType.WITHDRAW, Date.valueOf(LocalDate.now()), wallet.getId());
            transactionRepository.create(transaction);
            walletRepository.updateAmount(wallet.getBalance(), wallet.getId());

            System.out.println("Final balance: " + wallet.getBalance());
        } else {
            System.out.println("Insufficient funds");
        }

    }

    @Override
    public void deposit(double amount, Wallet wallet) throws SQLException {

        wallet.setBalance(wallet.getBalance() + amount);
        Transaction transaction = new Transaction(
                amount, TransactionType.DEPOSIT, Date.valueOf(LocalDate.now()), wallet.getId());
        transactionRepository.create(transaction);
        walletRepository.updateAmount(wallet.getBalance(), wallet.getId());
        System.out.println("Final balance: " + wallet.getBalance());
    }

    @Override
    public double displayRecentBalance(Wallet wallet) {
        return wallet.getBalance();
    }

    @Override
    public Wallet getWalletById(long id) throws SQLException {
        return walletRepository.read(id);
    }
}
