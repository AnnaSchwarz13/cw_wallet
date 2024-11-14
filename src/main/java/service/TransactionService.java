package service;

import entities.Transaction;
import entities.Wallet;

import java.util.List;

public interface TransactionService {

    void displayTransactions(List<Transaction> transactions);

    List<Transaction> getTransactions(Wallet wallet);
}
