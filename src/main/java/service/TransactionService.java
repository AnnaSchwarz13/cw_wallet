package service;

import entities.Transaction;
import entities.Wallet;

import java.util.List;

public interface TransactionService {

    List<Transaction> getTransactions(Wallet wallet);
}
