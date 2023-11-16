package com.example.movierec.controller;


import com.example.movierec.entity.User;
import com.example.movierec.mapper.UserMapper;
import com.example.movierec.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

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
     * 退出登录接口
     *
     * @param request 包含用户id
     * @return 退出结果
     */
    @GetMapping("logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("id");
        if (userService.logout(userId)) {
            return new ResponseEntity<>("退出成功", HttpStatus.OK);
        }
        return new ResponseEntity<>("退出失败", HttpStatus.BAD_REQUEST);
    }

    // TODO 刘

    /**
     * 用户注册接口
     *
     * @param user 用户信息
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<Object> register(@RequestBody User user) {
        boolean result = userService.saveUser(user);
        if (result) {
            return new ResponseEntity<>("用户注册成功", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("用户注册失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO 张

    /**
     * 获取个人信息接口
     *
     * @param request 包含用户id
     * @return info
     */
    @GetMapping("getInfo")
    public ResponseEntity<Object> getInfo(HttpServletRequest request) {
        try {
            Integer id = (Integer) request.getAttribute("id"); // 获取用户id
            User info=userService.findById(id);
            if (!Objects.isNull(info)){
                return new ResponseEntity<>(info,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("用户不存在",HttpStatus.NOT_FOUND);
            }
        } catch (NullPointerException e){
            return new ResponseEntity<>("请输入id",HttpStatus.BAD_REQUEST);
        }
    }

    // TODO 张

    /**
     * 修改个人信息接口
     *
     * @param user
     * @return
     */
    @PutMapping("changeInfo")
    public ResponseEntity<Object> changeInfo(@RequestBody User user) {
        try {
            userService.changeInfoById(user.getId(), user.getName(), user.getAccount(), user.getSex());
            return new ResponseEntity<>("修改成功",HttpStatus.OK);
        }catch (DataAccessException e){
            return new ResponseEntity<>("修改失败",HttpStatus.BAD_REQUEST);
        }
    }

    // TODO 刘

    /**
     * 修改密码接口
     *
     * @param oldPassword
     * @param newPassword
     * @param request
     * @return
     */
    @PutMapping("changePassword")
    public ResponseEntity<Object> changePassword(@RequestParam String oldPassword,
                                                 @RequestParam String newPassword,
                                                 HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute("id");
        User user = userService.findById(id); // 获取用户信息
        if(!user.getPassword().equals(oldPassword)) { // 验证旧密码是否正确
            return ResponseEntity.status(400).body("Old password is incorrect");
        }
        user.setPassword(newPassword); // 更新密码
        userService.updateUser(user); // 更新用户信息到数据库
        return ResponseEntity.ok("Password changed successfully");
    }
}
