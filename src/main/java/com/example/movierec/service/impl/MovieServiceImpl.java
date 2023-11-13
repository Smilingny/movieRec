package com.example.movierec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.movierec.entity.Movie;
import com.example.movierec.mapper.MovieMapper;
import com.example.movierec.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieMapper movieMapper;
    /**
     * 查询电影
     *
     * @param keyWord 电影名
     * @return 分页电影信息
     */
    @Override
    public IPage<Movie> searchMovie(String keyWord, int pageNumber, int pageSize) {
        QueryWrapper<Movie> movieQueryWrapper = new QueryWrapper<>();
        movieQueryWrapper.like("title", keyWord);
        IPage<Movie> movieIPage = new Page<>(pageNumber, pageSize);
        return movieMapper.selectPage(movieIPage, movieQueryWrapper);
    }
}
