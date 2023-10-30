package com.example.movierec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.movierec.entity.Genre;
import com.example.movierec.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GenreMapper extends BaseMapper<Genre> {

}
