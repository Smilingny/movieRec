package com.example.movierec.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.movierec.dto.MovieSimple;
import com.example.movierec.entity.Rating;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RatingMapper extends BaseMapper<Rating> {

    @Update("UPDATE rating r SET r.agree = r.agree + 1 ${ew.customSqlSegment}")
    Integer updateAgree(@Param(Constants.WRAPPER) Wrapper<Rating> queryWrapper);

    @Select("SELECT m.title, m.id, m.poster, m.duration, m.rating FROM movie m JOIN rating r ON m.id = r.movie ${ew.customSqlSegment}")
    IPage<MovieSimple> getMoviesByRating(IPage<MovieSimple> page, @Param(Constants.WRAPPER) Wrapper<Rating> queryWrapper);
}
