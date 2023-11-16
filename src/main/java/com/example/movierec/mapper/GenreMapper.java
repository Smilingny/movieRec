package com.example.movierec.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.movierec.dto.MovieSimple;
import com.example.movierec.entity.Genre;
import com.example.movierec.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface GenreMapper extends BaseMapper<Genre> {

    @Select("select m.* from movie m join genre_movie gm on m.id=gm.movie join genre g on gm.genre=g.id ${ew.customSqlSegment}")
    IPage<MovieSimple> getMovies(IPage<MovieSimple> page, @Param(Constants.WRAPPER) Wrapper<Genre> queryWrapper);

    @Select("select g.name from movie m join genre_movie gm on m.id=gm.movie join genre g on gm.genre=g.id ${ew.customSqlSegment}")
    List<String> getMovieGenres(@Param(Constants.WRAPPER) Wrapper<Movie> queryWrapper);

}
