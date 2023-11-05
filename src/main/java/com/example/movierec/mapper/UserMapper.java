package com.example.movierec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.movierec.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT COUNT(*) FROM users WHERE username = #{username}")
    boolean checkUsernameExists(@Param("username") String username);

    @Insert("INSERT INTO users(username, password) VALUES(#{username}, #{password})")
    void insertUser(@Param("username") String username, @Param("password") String password);
    void insertUserFull(User user);

    @Update("UPDATE users SET password = #{password} WHERE username = #{username}")
    void updateUserPassword(User user);

}
