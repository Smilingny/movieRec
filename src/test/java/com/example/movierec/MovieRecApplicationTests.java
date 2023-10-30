package com.example.movierec;

import com.auth0.jwt.interfaces.Claim;
import com.example.movierec.entity.Genre;
import com.example.movierec.entity.Movie;
import com.example.movierec.entity.User;
import com.example.movierec.mapper.GenreMapper;
import com.example.movierec.mapper.MovieMapper;
import com.example.movierec.mapper.UserMapper;
import com.example.movierec.util.JwtUtil;
import com.example.movierec.util.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class MovieRecApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private RedisCache redisCache;

//    private final JwtUtil jwtUtil = new JwtUtil();
    @Test
    void contextLoads() {
//        User user = new User("王刚","2020","pass",true);
//        userMapper.insert(user);
        List<User> users =userMapper.selectList(null);
        users.forEach(System.out::println);
        Map map = new HashMap();
        map.put("name","张完");
        User user = (User) userMapper.selectByMap(map).get(0);
        System.out.println(user);
    }



    @Test
    void f2(){
        if (redisCache.getCacheObject("user:2") == null) System.out.println("为空");


    }

}
