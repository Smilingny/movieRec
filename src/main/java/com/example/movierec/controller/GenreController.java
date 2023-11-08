package com.example.movierec.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.movierec.dto.MovieSimple;
import com.example.movierec.entity.Movie;
import com.example.movierec.service.GenreService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
@RequestMapping("")
public class GenreController {
    @Autowired
    private GenreService genreService;

    /**
     *  分页获取某类型的电影
     * @param pageNumber 页码
     * @param pageSize 分页大小
     * @param genre 电影类型
     * @param request 包含用户id
     * @return 电影列表(包含id, title, poster, rating, duration)按评分从高到低排序
     */
    @GetMapping("getMoviesByGenre")
    public ResponseEntity<Object> getMoviesByGenre(@RequestParam(value = "pageNumber") int pageNumber,
                                                   @RequestParam(value = "pageSize") int pageSize,
                                                   @RequestParam(value = "genre") String genre,
                                                   HttpServletRequest request) {
        IPage<MovieSimple> movies= genreService.getMoviesByGenre(genre, pageNumber, pageSize);
        if(!Objects.isNull(movies)){
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("获取失败", HttpStatus.BAD_REQUEST);
        }
    }
}
