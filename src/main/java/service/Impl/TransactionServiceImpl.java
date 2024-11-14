package service.Impl;

import entities.Transaction;
import entities.Wallet;
import repository.Impl.TransactionRepositoryImpl;
import service.TransactionService;

import java.util.List;


public class TransactionServiceImpl implements TransactionService {
    TransactionRepositoryImpl repository = new TransactionRepositoryImpl();
    @Override
    public void displayTransactions(List<Transaction> transactions) {//display should be in main
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

    @Override
    public List<Transaction> getTransactions(Wallet wallet) {
        return repository.allTransactions(wallet);
    }
}
