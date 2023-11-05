package com.example.movierec.service;

import com.example.movierec.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String login(String account, String password);
    boolean userExists(String username);
    void saveUser(User user);
}
