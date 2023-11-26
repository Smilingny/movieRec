package com.example.movierec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.movierec.dto.HistoryCount;
import com.example.movierec.entity.History;
import com.example.movierec.mapper.HistoryMapper;
import com.example.movierec.service.HistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryMapper historyMapper;

    public HistoryServiceImpl(HistoryMapper historyMapper) {
        this.historyMapper = historyMapper;
    }

    /**
     * 用户点击电影，插入历史记录
     *
     * @param userId  用户id
     * @param movieId 电影id
     */
    @Override
    public void clickMovie(Integer userId, Integer movieId) {
        QueryWrapper<History> queryWrapper = new QueryWrapper<>();
        History history = new History(userId, movieId);
        queryWrapper.setEntity(history);
        historyMapper.insert(history);
    }

    @Override
    public List<HistoryCount> getHistoryCounts(Integer userId) {
        return historyMapper.getHistoryCounts(userId);
    }

    @Override
    public List<HistoryCount> getMovieHistoryCounts(Integer movieId) {
        return historyMapper.getMovieHistoryCounts(movieId);
    }
}
