import entities.Transaction;
import entities.Wallet;
import repository.Impl.TransactionRepositoryImpl;
import repository.Impl.UserRepositoryImpl;
import repository.Impl.WalletRepositoryImpl;
import service.TransactionService;
import service.UserService;
import service.WalletService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

static UserService userService = new UserService();
static WalletRepositoryImpl walletRepository = new WalletRepositoryImpl();
static Scanner sc = new Scanner(System.in);

public static void main(String[] args) throws SQLException {

    while (UserService.loggedInUser == null) {
        System.out.println("Welcome to Wallet App");
        System.out.println("1.login");
        System.out.println("2.singUp");
        int option = sc.nextInt();
        loginMenu(option);
    }

    while (UserService.loggedInUser != null) {
        System.out.println("Welcome dear " + UserService.loggedInUser.getUsername());
        System.out.println("1.See your wallet's balance");
        System.out.println("2.Withdraw");
        System.out.println("3.Deposit");
        System.out.println("4.See wallet's transactions");
        System.out.println("5.Logout");

    }

}

public static void loginMenu(int option) throws SQLException {
    if (option == 1) {
        System.out.println("Please enter username");
        String username = sc.next();
        System.out.println("Please enter password");
        String password = sc.next();
        userService.userLogin(username, password);
    } else if (option == 2) {
        userService.userSignUp();
    }


}

public static void loggedInMenu(int option) throws SQLException {
    int walletId = (int) UserRepositoryImpl.findWalletIdByUserId(UserService.loggedInUser.getId());
    Wallet wallet = walletRepository.read(walletId);
    if (option == 1) {

        if (walletId != 0) {
            System.out.println("Your wallet balance is : \n" + WalletService.displayRecentBalance(wallet));
        }
    } else if (option == 2) {
        System.out.println("Enter amount to withdraw");
        double amount = sc.nextDouble();
        WalletService.withdraw(amount, wallet);
    } else if (option == 3) {
        System.out.println("Enter amount to deposit");
        double amount = sc.nextDouble();
        WalletService.deposit(amount, wallet);
    } else if (option == 4) {
        List<Transaction> transactions = TransactionRepositoryImpl.allTransactions(wallet);
        TransactionService.displayTransactions(transactions);
    } else if (option == 5) {
        userService.userLogout();
    }

}