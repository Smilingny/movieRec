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

    /**
     * 分页获取所有电影信息
     *
     * @param request
     * @return 电影列表
     */
    @GetMapping("getMovies")
    public ResponseEntity<Object> getMovies(@RequestParam(value = "pageNumber") int pageNumber,
                                            @RequestParam(value = "pageSize") int pageSize,
                                            HttpServletRequest request) {
        return null;
    }


}
