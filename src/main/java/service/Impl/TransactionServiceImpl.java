package service.Impl;

import entities.Transaction;
import entities.Wallet;
import exceptions.TransactionException;
import repository.Impl.TransactionRepositoryImpl;
import service.TransactionService;

import java.util.List;


public class TransactionServiceImpl implements TransactionService {
    TransactionRepositoryImpl repository = new TransactionRepositoryImpl();

    @Override
    public List<Transaction> getTransactions(Wallet wallet) throws TransactionException {
        if(repository.allTransactions(wallet).isEmpty()){
            throw new TransactionException("No transactions found");
        }
        return repository.allTransactions(wallet);
    }
}
