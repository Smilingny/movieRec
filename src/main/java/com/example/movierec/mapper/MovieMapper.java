package com.example.movierec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.movierec.entity.Movie;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieMapper extends BaseMapper<Movie> {
}
