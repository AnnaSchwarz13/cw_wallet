package service.Impl;

import entities.User;
import entities.Wallet;
import exceptions.UserException;
import repository.Impl.UserRepositoryImpl;
import repository.Impl.WalletRepositoryImpl;
import service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    public static User loggedInUser;
    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    WalletRepositoryImpl walletRepository = new WalletRepositoryImpl();
    AuthenticationServiceImpl authenticationService = new AuthenticationServiceImpl();

    @Override
    public void userSignUp(User userIn) throws SQLException, UserException {
                User user = new User(userIn.getUsername(), userIn.getPassword());
                if(!authenticationService.isUsernameNew(userIn.getUsername())) {
                    throw new UserException("Username is already exist");
                }
                user = userRepository.create(user);
                user.setId(userRepository.findByUsername(userIn.getUsername()));
                Wallet wallet = new Wallet(0, user);
                walletRepository.create(wallet);
                System.out.println("singUp successfully!");
        }


    @Override
    public void userLogin(String username, String password) throws UserException {
        for (User user : userRepository.all()) {
            if (username.equals(user.getUsername())) {
                if (password.equals(user.getPassword())) {
                    loggedInUser = user;
                    System.out.println("Logged in successfully!!");
                    return;
                }
            }
        }
        throw new UserException("Username or password is wrong!");
    }

    public void userLogout() throws UserException {
        if (userRepository.all().isEmpty()) {
            throw new UserException("no user loggedIn");
        } else {
            loggedInUser = null;
        }

    }

    public long getLoggedInUserWalletId() throws SQLException {
        return userRepository.findWalletIdByUserId(loggedInUser.getId());
    }

}
