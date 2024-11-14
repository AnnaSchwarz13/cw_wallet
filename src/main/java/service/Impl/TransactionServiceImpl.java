package service.Impl;

import entities.Transaction;
import entities.Wallet;
import repository.Impl.TransactionRepositoryImpl;
import service.TransactionService;

import java.util.List;


public class TransactionServiceImpl implements TransactionService {
    TransactionRepositoryImpl repository = new TransactionRepositoryImpl();

    @Override
    public List<Transaction> getTransactions(Wallet wallet) {
        return repository.allTransactions(wallet);
    }
}
