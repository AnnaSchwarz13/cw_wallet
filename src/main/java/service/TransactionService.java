package service;

import entities.Transaction;

import java.util.List;

public interface TransactionService {

    void displayTransactions(List<Transaction> transactions);
}
