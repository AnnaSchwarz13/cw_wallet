package service;

import entities.Transaction;

import java.util.List;

public class TransactionService {
    public static void displayTransactions(List<Transaction> transactions) {
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
