package com.example.movierec.controller;


import com.example.movierec.entity.User;
import com.example.movierec.mapper.UserMapper;
import com.example.movierec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登陆接口
     *
     * @param account  用户账号
     * @param password 用户密码
     * @return jwt
     */
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestParam(value = "account") String account,
                                        @RequestParam(value = "password") String password) {
        try {
            String jwt = userService.login(account, password);
            if (!Objects.isNull(jwt)) {
                return new ResponseEntity<>(jwt, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("密码错误", HttpStatus.UNAUTHORIZED);
            }
        } catch (NullPointerException e) {
            return new ResponseEntity<>("用户不存在", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 用户注册接口
     *
     * @param user 用户信息
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<Object> register(@RequestBody User user) {
        return null;
    }

    /**
     * 获取个人信息接口
     * @param request
     * @return
     */
    @GetMapping("getInfo")
    public ResponseEntity<Object> getInfo(HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute("id"); // 获取用户id
        return null;
    }

    /**
     * 修改个人信息接口
     * @param user
     * @return
     */
    @PutMapping("changeInfo")
    public ResponseEntity<Object> changeInfo(@RequestBody User user){
        return null;
    }

    /**
     * 修改密码接口
     * @param oldPassword
     * @param newPassword
     * @param request
     * @return
     */
    @PutMapping("changePassword")
    public ResponseEntity<Object> changePassword(@RequestParam String oldPassword,
                                                 @RequestParam String newPassword,
                                                 HttpServletRequest request){
        return null;
    }
}
