package com.example.movierec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.movierec.dto.MovieSimple;
import com.example.movierec.entity.Rating;
import com.example.movierec.mapper.RatingMapper;
import com.example.movierec.service.RateService;
import com.example.movierec.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RateServiceImpl implements RateService {
    private final RatingMapper ratingMapper;
    private final RecommendService recommendService;

    public RateServiceImpl(RatingMapper ratingMapper, RecommendService recommendService) {
        this.ratingMapper = ratingMapper;
        this.recommendService = recommendService;
    }

    /**
     * 评价电影
     *
     * @param userId  用户id
     * @param movieId 电影id
     * @param score   分数
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
            return ratingMapper.update(rating, existWrapper) != 0;
        } else {
            recommendService.Recommend(userId);
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
        ratingQueryWrapper.eq("id", ratingId);
        return ratingMapper.updateAgree(ratingQueryWrapper) == 1;
    }

    @Override
    public String selectRating(Integer movieId, Integer userId) {
        QueryWrapper<Rating> selectratingQueryWrapper = new QueryWrapper<>();
        selectratingQueryWrapper
                .eq("movie", movieId)
                .eq("user", userId);
        Rating result = ratingMapper.selectOne(selectratingQueryWrapper);
        if (result == null) {
            return null;
        } else {
            return result.toString();
        }
    }

    @Override
    public List<Rating> selectAllRating(Integer movieId) {
        QueryWrapper<Rating> selectallratingQueryWrapper = new QueryWrapper<>();
        selectallratingQueryWrapper
                .eq("movie", movieId);
        return ratingMapper.selectList(selectallratingQueryWrapper);
    }

    @Override
    public IPage<MovieSimple> getMoviesByRating(int rating, int pageNumber, int pageSize) {
        QueryWrapper<Rating> ratingQueryWrapper = new QueryWrapper<>();
        if (rating == 1) {
            ratingQueryWrapper.orderByDesc("r.score")
                    .between("r.score", 0, 2);
        } else if (rating == 2) {
            ratingQueryWrapper.orderByDesc("r.score")
                    .between("r.score", 2, 4);
        } else if (rating == 3) {
            ratingQueryWrapper.orderByDesc("r.score")
                    .between("r.score", 4, 6);
        } else if (rating == 4) {
            ratingQueryWrapper.orderByDesc("r.score")
                    .between("r.score", 6, 8);
        } else if (rating == 5) {
            ratingQueryWrapper.orderByDesc("r.score")
                    .between("r.score", 8, 10);
        }
        IPage<MovieSimple> page = new Page<>(pageNumber, pageSize);
        return ratingMapper.getMoviesByRating(page, ratingQueryWrapper);
    }

}
