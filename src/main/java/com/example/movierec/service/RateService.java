package com.example.movierec.service;

import org.springframework.stereotype.Service;

@Service
public interface RateService {
    /**
     *  评价电影
     * @param userId 用户id
     * @param movieId 电影id
     * @param score 分数
     * @param comment 评价内容
     * @return 评价结果
     */
    Boolean ratingMovie(Integer userId, Integer movieId, Double score, String comment);

    /**
     * 点赞电影点评
     * @param ratingId 评论ID
     * @return 评论结果
     */
    Boolean agreeOnRating(Integer ratingId);
}
