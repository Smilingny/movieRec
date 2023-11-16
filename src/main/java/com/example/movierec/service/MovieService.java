package com.example.movierec.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.movierec.dto.MovieSimple;
import com.example.movierec.entity.Movie;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {
    /**
     * 查询电影
     * @param keyWord 电影名
     * @return 分页电影信息
     */
    IPage<Movie> searchMovie(String keyWord, int pageNumber, int pageSize);
    IPage<MovieSimple> getPagedMoviesSortedByRating(int pageNumber, int pageSize);
    Movie getMovieById(Integer movieId);
}
