package com.example.movierec.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.movierec.dto.MovieSimple;
import com.example.movierec.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenreService {
    /**
     * 分页获取某个类型的电影
     * @param genre 类型名
     * @param pageNumber 页码
     * @param pageSize 页大小
     * @return 分页电影数据
     */
    IPage<MovieSimple> getMoviesByGenre(String genre, int pageNumber, int pageSize);

    /**
     * 获取电影的类型
     * @param movieId 电影id
     * @return 电影的类型列表
     */
    List<String> getMovieGenres(Integer movieId);
}
