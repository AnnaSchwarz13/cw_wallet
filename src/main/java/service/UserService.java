package service;

import entities.User;
import repository.Impl.UserRepositoryImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserService {
    public static User loggedInUser;
    List<User> users = UserRepositoryImpl.all() ;
    Scanner scanner = new Scanner(System.in);
    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    public void userSignUp() throws SQLException {

        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        User user = new User(username, password);
         userRepository.create(user);
    }

    public void userLogin(String username, String password) {
        if(!users.isEmpty()){
            for(User user : users){
                if(username.equals(user.getUsername())){
                    if(password.equals(user.getPassword())){
                        loggedInUser = user;
                        System.out.println("Logged in successfully!!");
                    }
                }
            }
        }

    }

    public void userLogout() {
        if(users.isEmpty()){
            System.out.println("No user logged in!");
        }
        else{
            loggedInUser = null;
        }

    }

}
