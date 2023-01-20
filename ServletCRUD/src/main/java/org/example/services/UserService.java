package org.example.services;

import org.example.repository.UserRepository;
import org.example.utils.Validation;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public List<String> getRolesByEmail(String email) throws Exception {
        if (!Validation.isEmail(email)) {
            throw new Exception("Email or password is invalid");
        }
        return userRepository.getUserRoles(email);
    }
}
