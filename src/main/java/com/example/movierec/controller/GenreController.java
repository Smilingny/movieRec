package com.example.movierec.controller;

import com.example.movierec.service.GenreService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
     * @param request
     * @return 电影列表
     */
    @GetMapping("getMoviesByGenre")
    public ResponseEntity<Object> getMoviesByGenre(@RequestParam(value = "pageNumber") int pageNumber,
                                                   @RequestParam(value = "pageSize") int pageSize,
                                                   @RequestParam(value = "genre") String genre,
                                                   HttpServletRequest request) {
        return null;
    }
}
