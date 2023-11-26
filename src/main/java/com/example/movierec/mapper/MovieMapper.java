package com.example.movierec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.movierec.dto.MovieSimple;
import com.example.movierec.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovieMapper extends BaseMapper<Movie> {
    @Select("SELECT id, title, poster, rating, duration FROM movie ORDER BY rating DESC LIMIT #{offset}, #{size}")
    List<MovieSimple> selectMovies(@Param("offset") int offset, @Param("size") int size);
    @Select("SELECT * FROM movie WHERE id = #{movieId}")
    Movie selectMovieById(@Param("movieId") Integer movieId);

    // 从movie表中统计数据，根据released字段的年份分组，每十年为一组，统计每组的电影数量
    @Select("SELECT COUNT(*) FROM movie m " +
            "JOIN rating r ON m.id=r.movie " +
            "JOIN user u ON u.id=r.user " +
            "WHERE u.id=#{userId} AND YEAR(released) BETWEEN #{start} AND #{end}")
    Integer selectMovieCountByYear(@Param("userId") Integer userId, @Param("start") Integer start, @Param("end") Integer end);
}
