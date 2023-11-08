package com.example.movierec.controller;

import com.example.movierec.service.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class MovieController {
    @Autowired
    private MovieService movieService;

    // TODO 刘
    /**
     * 分页获取所有电影信息（只包含id, title, poster, rating, duration）
     *
     * @param request
     * @return 电影列表（只包含id, title, rating, poster, duration）按评分从高到低排序
     */
    @GetMapping("getMovies")
    public ResponseEntity<Object> getMovies(@RequestParam(value = "pageNumber") int pageNumber,
                                            @RequestParam(value = "pageSize") int pageSize,
                                            HttpServletRequest request) {
        return null;
    }

    // TODO 刘
    /**
     * 获取电影详细信息
     * @param movieId
     * @return
     */
    @GetMapping("getMovieInfo")
    public ResponseEntity<Object> getMovieInfo(@RequestParam(value = "movieId") Integer movieId){
        return null;
    }

}
