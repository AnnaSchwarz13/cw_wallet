package service;

import entities.Transaction;
import entities.Wallet;
import exceptions.TransactionException;

import java.util.List;

public interface TransactionService {

    List<Transaction> getTransactions(Wallet wallet) throws TransactionException;
}
