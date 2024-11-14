package service.Impl;

import entities.User;
import repository.Impl.UserRepositoryImpl;
import service.AuthenticationService;

import java.util.List;

public  class AuthenticationServiceImpl implements AuthenticationService {
    public boolean isUsernameNew(String username) {
        List<User> users = UserRepositoryImpl.all();
        for(User user : users){
            if(user.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }
}
