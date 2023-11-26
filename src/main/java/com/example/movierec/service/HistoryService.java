package com.example.movierec.service;

import com.example.movierec.dto.HistoryCount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HistoryService {
    /**
     * 用户点击电影，插入历史记录
     * @param userId 用户id
     * @param movieId 电影id
     */
    void clickMovie(Integer userId, Integer movieId);

    List<HistoryCount> getHistoryCounts(Integer userId);

    List<HistoryCount> getMovieHistoryCounts(Integer movieId);
}
