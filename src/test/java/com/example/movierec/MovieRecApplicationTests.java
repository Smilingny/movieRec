package com.example.movierec;

import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.movierec.dto.MovieSimple;
import com.example.movierec.entity.Genre;
import com.example.movierec.entity.Movie;
import com.example.movierec.entity.Rating;
import com.example.movierec.entity.User;
import com.example.movierec.mapper.GenreMapper;
import com.example.movierec.mapper.MovieMapper;
import com.example.movierec.mapper.RatingMapper;
import com.example.movierec.mapper.UserMapper;
import com.example.movierec.service.*;
import com.example.movierec.util.JwtUtil;
import com.example.movierec.util.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
    private UserService userService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private RatingMapper ratingMapper;
    @Autowired
    private RateService rateService;
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RedisCache redisCache;


    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张完");
        User user = (User) userMapper.selectByMap(map).get(0);
        System.out.println(user);
    }


    @Test
    void f2() {
        genreService.getMovieGenres(1).forEach(System.out::println);
    }

    @Test
    void f3() {
        QueryWrapper<Genre> genreQueryWrapper = new QueryWrapper<>();
        genreQueryWrapper.eq("name", "Action");
        IPage<MovieSimple> movieIPage = new Page<>(1, 10);
        IPage<MovieSimple> moviePage = genreMapper.getMovies(movieIPage, genreQueryWrapper);
        System.out.println();
    }

    @Test
    void py() {
        String djangoServiceUrl = "http://localhost:8000/python/";  // Django服务的URL
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(djangoServiceUrl)
                .queryParam("user_id", "1");
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(builder.toUriString(), String.class);
        System.out.println("Response from Django (Python) service: " + response);
    }


    @Test
    void rate() {
        System.out.println(historyService.getHistoryCounts(1));
    }
}
