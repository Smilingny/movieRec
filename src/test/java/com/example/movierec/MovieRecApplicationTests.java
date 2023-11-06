package com.example.movierec;

import com.auth0.jwt.interfaces.Claim;
import com.example.movierec.entity.Genre;
import com.example.movierec.entity.Movie;
import com.example.movierec.entity.Rating;
import com.example.movierec.entity.User;
import com.example.movierec.mapper.GenreMapper;
import com.example.movierec.mapper.MovieMapper;
import com.example.movierec.mapper.RatingMapper;
import com.example.movierec.mapper.UserMapper;
import com.example.movierec.service.RateService;
import com.example.movierec.util.JwtUtil;
import com.example.movierec.util.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
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
    private RatingMapper ratingMapper;
    @Autowired
    private RateService rateService;

    @Autowired
    private RedisCache redisCache;


    @Test
    void contextLoads() {
        List<User> users =userMapper.selectList(null);
        users.forEach(System.out::println);
        Map map = new HashMap();
        map.put("name","张完");
        User user = (User) userMapper.selectByMap(map).get(0);
        System.out.println(user);
    }



    @Test
    void f2(){
        System.out.println(rateService.ratingMovie(1, 2, 2.5, "hello"));
    }

    @Test
    void f3(){
        User user = new User();
        user.setAccount("2028");
        user.setPassword("pass");
        userMapper.insert(user);
    }

    @Test
    void py(){
        String djangoServiceUrl = "http://localhost:8000/python/";  // Django服务的URL
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(djangoServiceUrl, String.class);

        System.out.println("Response from Django (Python) service: " + response);
    }
}
