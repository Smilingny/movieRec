package com.example.movierec.service;

import com.example.movierec.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**
     * 登录功能
     * @param account 账号
     * @param password 密码
     * @return jwt
     */
    String login(String account, String password);

    /**
     * 用户退出登录功能
     * @param userId 用户id
     * @return 退出结果
     */
    Boolean logout(Integer userId);

    boolean userExists(String username);
    void saveUser(User user);

}
