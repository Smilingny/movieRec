package com.example.movierec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.movierec.dto.MovieSimple;
import com.example.movierec.entity.Recommend;
import com.example.movierec.mapper.RecommendMapper;
import com.example.movierec.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {

    private final RecommendMapper recommendMapper;

    public RecommendServiceImpl(RecommendMapper recommendMapper) {
        this.recommendMapper = recommendMapper;
    }
    /**
     * 分页获取电影列表
     *
     * @param pageNumber 页码
     * @param pageSize   分页大小
     * @param userId     用户id
     * @return 电影列表
     */
    @Override
    public IPage<MovieSimple> getRecommend(int pageNumber, int pageSize, Integer userId) {
        QueryWrapper<Recommend> recommendQueryWrapper = new QueryWrapper<>();
        recommendQueryWrapper.eq("user",userId)
                .orderBy(true,false,"date");
        IPage<MovieSimple> simpleIPage = new Page<>(pageNumber,pageSize);
        return recommendMapper.getRecommend(simpleIPage, recommendQueryWrapper);
    }

    /**
     * 为用户推荐电影
     *
     * @param userId 用户id
     */
    @Override
    @Async
    public void Recommend(Integer userId) {
        String djangoServiceUrl = "http://localhost:8000/python/";  // Django服务的URL
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(djangoServiceUrl)
                .queryParam("user_id", "1");
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(builder.toUriString(), String.class);
        response=response.replace("[","");
        response=response.replace("]","");
        String[] split = response.split(",");
        List<Integer> movies = new ArrayList<>();
        QueryWrapper<Recommend> recommendQueryWrapper = new QueryWrapper<>();
        for (String s : split) {
            if (Integer.parseInt(s)<4457) {
                recommendQueryWrapper.eq("movie", s)
                        .eq("user", userId);
                if (recommendMapper.selectOne(recommendQueryWrapper) == null) {
                    recommendMapper.insert(new Recommend(Integer.parseInt(s), userId));
                }
            }
        }

    }
}
