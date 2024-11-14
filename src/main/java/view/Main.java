package view;

import entities.Transaction;
import entities.User;
import entities.Wallet;
import exceptions.TransactionException;
import exceptions.UserException;
import exceptions.WalletExceptions;
import service.Impl.TransactionServiceImpl;
import service.Impl.UserServiceImpl;
import service.Impl.WalletServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static UserServiceImpl userServiceImpl = new UserServiceImpl();
    static TransactionServiceImpl transactionService = new TransactionServiceImpl();
    static WalletServiceImpl walletServiceImpl = new WalletServiceImpl();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] ignoredArgs) throws SQLException {

        while (true) {
            while (UserServiceImpl.loggedInUser == null) {
                System.out.println("\n\nWelcome to Wallet App");
                System.out.println("1.login");
                System.out.println("2.singUp");
                int option = sc.nextInt();
                loginMenu(option);
            }

            while (UserServiceImpl.loggedInUser != null) {
                System.out.println("\n\nWelcome dear " + UserServiceImpl.loggedInUser.getUsername());
                System.out.println("1.See your wallet's balance");
                System.out.println("2.Withdraw");
                System.out.println("3.Deposit");
                System.out.println("4.See wallet's transactions");
                System.out.println("5.Logout");
                int option = sc.nextInt();
                loggedInMenu(option);
            }

        }
    }

    public static void loginMenu(int option) throws SQLException {
        if (option == 1) {
            System.out.println("Please enter username");
            String username = sc.next();
            System.out.println("Please enter password");
            String password = sc.next();
            try {
                userServiceImpl.userLogin(username, password);
            } catch (UserException e) {
                System.out.println(e.getMessage());
            }
        } else if (option == 2) {
            System.out.println("Enter username:");
            String username = sc.nextLine() + sc.next();
            System.out.println("Enter password:");
            String password = sc.nextLine() + sc.next();
            User user = new User(username, password);
            try {

                userServiceImpl.userSignUp(user);
            } catch (UserException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void loggedInMenu(int option) throws SQLException{
        Wallet wallet = walletServiceImpl.getWalletById(userServiceImpl.getLoggedInUserWalletId());
        if (option == 1) {
            try {
                if (userServiceImpl.getLoggedInUserWalletId() == 0) {
                    throw new WalletExceptions("you have no wallet yet");
                }
                System.out.println("Your wallet balance is : \n" + walletServiceImpl.displayRecentBalance(wallet));
            } catch (WalletExceptions e) {
                System.out.println(e.getMessage());
            }
        } else if (option == 2) {
            System.out.println("Enter amount to withdraw");
            double amount = sc.nextDouble();
            try {
                walletServiceImpl.withdraw(amount, wallet);
            } catch (WalletExceptions e) {
                System.out.println(e.getMessage());
            }

        } else if (option == 3) {
            System.out.println("Enter amount to deposit");
            double amount = sc.nextDouble();
            walletServiceImpl.deposit(amount, wallet);
        } else if (option == 4) {
            List<Transaction> transactions = transactionService.getTransactions(wallet);
            displayTransactions(transactions);
        } else if (option == 5) {
            try {
                userServiceImpl.userLogout();
            } catch (UserException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void displayTransactions(List<Transaction> transactions) {//display should be in main
        try {
            if (transactions.isEmpty()) {
                throw new TransactionException("No transactions found");
            }
            for (Transaction transaction : transactions) {
                System.out.println("----------");
                System.out.println(transaction.getType());
                System.out.println(transaction.getAmount());
                System.out.println(transaction.getDate());
            }
        }catch (TransactionException e) {
            System.out.println(e.getMessage());
        }
    }
}