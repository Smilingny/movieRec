package com.example.movierec.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String login(String account, String password);
}
