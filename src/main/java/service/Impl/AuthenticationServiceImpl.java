package service.Impl;

import entities.User;
import repository.Impl.UserRepositoryImpl;
import repository.UserRepository;
import service.AuthenticationService;

import java.util.List;

public class AuthenticationServiceImpl implements AuthenticationService {
    static UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public boolean isUsernameNew(String username) {
        List<User> users = userRepository.all();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
}
