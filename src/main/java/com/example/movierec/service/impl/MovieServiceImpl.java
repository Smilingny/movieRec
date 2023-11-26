package com.example.movierec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.movierec.dto.MovieSimple;
import com.example.movierec.entity.Movie;
import com.example.movierec.mapper.MovieMapper;
import com.example.movierec.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieMapper movieMapper;

    public MovieServiceImpl(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }
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
    @Override
    public IPage<MovieSimple> getPagedMoviesSortedByRating(int pageNumber, int pageSize) {
        // 计算offset
        int offset = (pageNumber - 1) * pageSize;
        // 调用MovieMapper的方法获取电影列表
        List<MovieSimple> movies = movieMapper.selectMovies(offset, pageSize);
        // 创建Page对象并设置数据
        Page<MovieSimple> page = new Page<>(pageNumber, pageSize);
        page.setRecords(movies);

        return page;
    }
    @Override
    public Movie getMovieById(Integer movieId) {
        // 调用MovieMapper的方法根据ID查询电影信息，这里假设为selectMovieById方法
        return movieMapper.selectMovieById(movieId);
    }

    /**
     *
     * @param userId 用户id
     * @return 数量列表
     */
    @Override
    public List<Integer> getMovieCountsByYear(Integer userId) {
        List<Integer> movieCounts = new ArrayList<>();
        for (int i = 1920; i <= 2010; i += 10) {
            movieCounts.add(movieMapper.selectMovieCountByYear(userId, i, i + 9));
        }
        return movieCounts;
    }


}
