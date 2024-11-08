package service;

import entities.Wallet;
import repository.Impl.WalletRepositoryImpl;

import java.sql.SQLException;

public class WalletService {


    public void withdraw(double amount,Wallet wallet) throws SQLException {
          if(wallet.getBalance() >= amount){
              wallet.setBalance(wallet.getBalance() - amount);
          }
        WalletRepositoryImpl.updateAmount(wallet.getBalance(),wallet.getId());
    }
    public void deposit(double amount,Wallet wallet) throws SQLException {

         wallet.setBalance(wallet.getBalance() + amount);
        WalletRepositoryImpl.updateAmount(wallet.getBalance(),wallet.getId());
    }
    public double displayRecentBalance(Wallet wallet){
        return wallet.getBalance();
    }
}
