package service;

import entities.User;
import repository.Impl.UserRepositoryImpl;

import java.util.List;

public  class AuthenticationService {
    public static boolean isUsernameNew(String username) {
        List<User> users = UserRepositoryImpl.all();
        for(User user : users){
            if(user.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }
}
