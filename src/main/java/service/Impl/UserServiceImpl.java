package service.Impl;

import entities.User;
import entities.Wallet;
import repository.Impl.UserRepositoryImpl;
import repository.Impl.WalletRepositoryImpl;
import service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    public static User loggedInUser;
    Scanner scanner = new Scanner(System.in);
    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    WalletRepositoryImpl walletRepository = new WalletRepositoryImpl();
    AuthenticationServiceImpl authenticationService = new AuthenticationServiceImpl();
    public void userSignUp() throws SQLException {
       while(true) {
           System.out.println("Enter username:");
           String username = scanner.nextLine();
           if (authenticationService.isUsernameNew(username)) {
               System.out.println("Enter password:");
               String password = scanner.nextLine();
               User user = new User(username, password);
               user =  userRepository.create(user);
               user.setId(UserRepositoryImpl.findByUsername(username));
               Wallet wallet = new Wallet(0,user);
               walletRepository.create(wallet);
               System.out.println("singUp successfully!");
               break;
           }
           System.out.println("this username is already in use!");
       }
    }

    public void userLogin(String username, String password) {
            for(User user : UserRepositoryImpl.all()){
                if(username.equals(user.getUsername())){
                    if(password.equals(user.getPassword())){
                        loggedInUser = user;
                        System.out.println("Logged in successfully!!");
                        return;
                    }
                }
            }
            System.out.println("username or password is incorrect!!");


    }

    public void userLogout() {
        if(UserRepositoryImpl.all().isEmpty()){
            System.out.println("No user logged in!");
        }
        else{
            loggedInUser = null;
        }

    }

}
