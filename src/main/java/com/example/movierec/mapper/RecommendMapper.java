package com.example.movierec.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.movierec.dto.MovieSimple;
import com.example.movierec.entity.Recommend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RecommendMapper extends BaseMapper<Recommend> {

    @Select("select m.id,m.title,m.rating,m.poster,m.duration from movie m join recommend r on r.movie=m.id " +
            "${ew.customSqlSegment}")
    IPage<MovieSimple> getRecommend(IPage<MovieSimple> page, @Param(Constants.WRAPPER) Wrapper<Recommend> queryWrapper);
}
