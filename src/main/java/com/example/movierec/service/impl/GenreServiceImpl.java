package com.example.movierec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.movierec.dto.MovieSimple;
import com.example.movierec.entity.Genre;
import com.example.movierec.entity.Movie;
import com.example.movierec.mapper.GenreMapper;
import com.example.movierec.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreMapper genreMapper;

    /**
     * 分页获取某个类型的电影
     * @param genre 类型名
     * @param pageNumber 页码
     * @param pageSize 页大小
     * @return 分页电影数据
     */
    public IPage<MovieSimple> getMoviesByGenre(String genre, int pageNumber, int pageSize){
        QueryWrapper<Genre> genreQueryWrapper = new QueryWrapper<>();
        genreQueryWrapper.eq("name", genre);
        genreQueryWrapper.orderBy(true,false,"rating");
        IPage<MovieSimple> page = new Page<>(pageNumber, pageSize);
        return genreMapper.getMovies(page, genreQueryWrapper);
    }

    /**
     * 获取电影的类型
     *
     * @param movieId 电影id
     * @return 电影的类型列表
     */
    @Override
    public List<String> getMovieGenres(Integer movieId) {
        QueryWrapper<Movie> movieQueryWrapper = new QueryWrapper<>();
        movieQueryWrapper.eq("m.id",movieId);
        return genreMapper.getMovieGenres(movieQueryWrapper);
    }
}
