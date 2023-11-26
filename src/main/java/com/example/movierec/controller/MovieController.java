package com.example.movierec.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.movierec.dto.MovieSimple;
import com.example.movierec.entity.Movie;
import com.example.movierec.service.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // TODO 刘

    /**
     * 分页获取所有电影信息（只包含id, title, poster, rating, duration）
     * 使用dto.MovieSimple包装
     *
     * @return 电影列表（只包含id, title, rating, poster, duration）按评分从高到低排序
     */
    @GetMapping("getMovies")
    public ResponseEntity<Object> getMovies(@RequestParam(value = "pageNumber") int pageNumber,
                                            @RequestParam(value = "pageSize") int pageSize) {
        // 调用MovieService的方法获取分页电影列表，并按评分排序
        IPage<MovieSimple> movieList = movieService.getPagedMoviesSortedByRating(pageNumber, pageSize);
        // 返回电影列表
        return ResponseEntity.ok(movieList);
    }

    // TODO 刘

    /**
     * 获取电影详细信息
     *
     * @param movieId 电影id
     * @return 电影详细信息
     */
    @GetMapping("getMovieInfo")
    public ResponseEntity<Object> getMovieInfo(@RequestParam(value = "movieId") Integer movieId) {
        Movie movie = movieService.getMovieById(movieId);
        if (movie == null) {
            return new ResponseEntity<>("电影不存在", HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(movie);
        }
    }


    /**
     * 根据电影名称关键词搜索电影
     *
     * @param pageNumber 页码
     * @param pageSize   分页大小
     * @param keyWord    电影名称关键词
     * @return 电影列表
     */
    @GetMapping("searchMovie")
    public ResponseEntity<Object> searchMovie(@RequestParam(value = "pageNumber") int pageNumber,
                                              @RequestParam(value = "pageSize") int pageSize,
                                              @RequestParam(value = "keyWord") String keyWord) {
        IPage<Movie> movies = movieService.searchMovie(keyWord, pageNumber, pageSize);
        if (Objects.isNull(movies)) {
            return new ResponseEntity<>("查询失败", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
    }

    @GetMapping("getMovieCountByYear")
    public ResponseEntity<Object> getMovieCountByYear(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(movieService.getMovieCountsByYear(userId));
    }
}
