package com.example.movierec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.movierec.dto.HistoryCount;
import com.example.movierec.entity.History;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HistoryMapper extends BaseMapper<History> {

    // 根据time字段的天数统计某个用户每天的浏览记录条数
    @Select("SELECT DATE(time) AS date, COUNT(*) AS count " +
            "FROM history " +
            "WHERE user=#{userId} " +
            "GROUP BY DATE(time) " +
            "ORDER BY DATE(time);")
    List<HistoryCount> getHistoryCounts(Integer userId);

    // 根据time字段的天数统计某个电影每天的浏览记录条数
    @Select("SELECT DATE(time) AS date, COUNT(*) AS count " +
            "FROM history " +
            "WHERE movie=#{movieId} " +
            "GROUP BY DATE(time) " +
            "ORDER BY DATE(time);")
    List<HistoryCount> getMovieHistoryCounts(Integer movieId);
}
