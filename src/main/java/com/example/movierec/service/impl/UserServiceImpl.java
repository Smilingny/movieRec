package com.example.movierec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.movierec.entity.User;
import com.example.movierec.mapper.UserMapper;
import com.example.movierec.service.UserService;
import com.example.movierec.util.JwtUtil;
import com.example.movierec.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisCache redisCache;
    @Override
    public String login(String account, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("account", account);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user.getPassword().equals(password)) {
            String key = "user:"+user.getId();
            redisCache.setCacheObject(key,user,7, TimeUnit.DAYS);
            return JwtUtil.createToken(user);
        }
        return null;
    }
    @Override
    public boolean userExists(String username) {
        return userMapper.checkUsernameExists(username);
    }
    @Override
    public void saveUser(User user) {
        if (!userExists(user.getUsername())) {
            userMapper.insertUserFull(user);
        } else {
            throw new RuntimeException("用户名已存在");
        }
    }
    @Override
    public void updatePassword(User user) {
        // 检查用户是否存在
        if (!userExists(user.getUsername())) {
            throw new RuntimeException("用户不存在");
        }
        // 检查新密码是否为空
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("新密码不能为空");
        }
        // 调用UserMapper的方法更新密码
        userMapper.updateUserPassword(user);
    }
}
