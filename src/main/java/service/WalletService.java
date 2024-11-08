package service;

import entities.Wallet;

public class WalletService {


    public void withdraw(double amount,Wallet wallet){
          if(wallet.getBalance() < amount){
              wallet.setBalance(wallet.getBalance() - amount);
          }
    }
    public void deposit(double amount,Wallet wallet){

         wallet.setBalance(wallet.getBalance() + amount);
    }
    public double displayRecentBalance(Wallet wallet){
        return wallet.getBalance();
    }
}
