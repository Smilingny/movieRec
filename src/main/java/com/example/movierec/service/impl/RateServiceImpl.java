package com.example.movierec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.movierec.entity.Rating;
import com.example.movierec.mapper.RatingMapper;
import com.example.movierec.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RateServiceImpl implements RateService {
    @Autowired
    private RatingMapper ratingMapper;

    /**
     *  评价电影
     * @param userId 用户id
     * @param movieId 电影id
     * @param score 分数
     * @param comment 评价内容
     * @return 评价结果
     */
    @Override
    public Boolean ratingMovie(Integer userId, Integer movieId, Double score, String comment) {
        Rating rating = new Rating(userId, movieId, score, comment);
        QueryWrapper<Rating> existWrapper = new QueryWrapper<>();
        existWrapper.eq("user", userId)
                .eq("movie", movieId);
        if (ratingMapper.exists(existWrapper)) {
            return ratingMapper.update(rating,existWrapper) != 0;
        } else {
            return ratingMapper.insert(rating) != 0;
        }
    }

    /**
     * 点赞电影点评
     *
     * @param ratingId 评论ID
     * @return 评论结果
     */
    @Override
    public Boolean agreeOnRating(Integer ratingId) {
        QueryWrapper<Rating> ratingQueryWrapper = new QueryWrapper<>();
        ratingQueryWrapper.eq("id",ratingId);
        return ratingMapper.updateAgree(ratingQueryWrapper) == 1;
    }
}
