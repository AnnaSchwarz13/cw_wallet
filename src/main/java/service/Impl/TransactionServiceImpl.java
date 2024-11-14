package service.Impl;

import entities.Transaction;
import service.TransactionService;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    public void displayTransactions(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found");
            return;
        }
        for (Transaction transaction : transactions) {
            System.out.println("----------");
            System.out.println(transaction.getType());
            System.out.println(transaction.getAmount());
            System.out.println(transaction.getDate());
        }

    }
}
