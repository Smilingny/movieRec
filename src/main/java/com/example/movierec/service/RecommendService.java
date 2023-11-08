package com.example.movierec.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.movierec.dto.MovieSimple;
import org.springframework.stereotype.Service;

@Service
public interface RecommendService {
    /**
     * 分页获取电影列表
     * @param pageNumber 页码
     * @param pageSize 分页大小
     * @param userId 用户id
     * @return 电影列表
     */
    IPage<MovieSimple> getRecommend(int pageNumber, int pageSize, Integer userId);
}
