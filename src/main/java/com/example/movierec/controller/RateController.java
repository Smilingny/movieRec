package com.example.movierec.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.movierec.dto.MovieSimple;
import com.example.movierec.entity.Rating;
import com.example.movierec.service.RateService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/")
public class RateController {
    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    /**
     * 评价电影
     *
     * @param movieId 电影id
     * @param score   评分
     * @param comment 评价
     * @return 评价结果
     */
    @PutMapping("ratingMovie")
    public ResponseEntity<Object> ratingMovie(@RequestParam(value = "movieId") Integer movieId,
                                              @RequestParam(value = "rate") Double score,
                                              @RequestParam(value = "comment") String comment,
                                              HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("id");
        if (rateService.ratingMovie(userId, movieId, score, comment)) {
            return new ResponseEntity<>("评价成功", HttpStatus.OK);
        }
        return new ResponseEntity<>("评价失败", HttpStatus.BAD_REQUEST);
    }

    // TODO 张

    /**
     * 获取用户对某个电影的评价信息
     * <b>只获取该用户对该电影的评价（只有一条）</b>
     *
     * @param movieId 电影id
     * @param request 包含用户id
     * @return 评分信息（时间、分数、评价、赞同数）
     */
    @GetMapping("getRating")
    public ResponseEntity<Object> getRating(@RequestParam(value = "movieId") Integer movieId,
                                            HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("id");
        String rating = rateService.selectRating(movieId, userId);
        if (rating.isEmpty()) {
            return new ResponseEntity<>("评价不存在", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(rating, HttpStatus.OK);
        }

    }

    // TODO 张

    /**
     * 分页获取某个电影的所有评价信息
     *
     * @param movieId 电影id
     * @return
     */
    @GetMapping("getAllRatings")
    public ResponseEntity<Object> getAllRatings(@RequestParam(value = "movieId") Integer movieId,
                                                @RequestParam(value = "pageNumber") int pageNumber,
                                                @RequestParam(value = "pageSize") int pageSize) {
        List<Rating> allrating = rateService.selectAllRating(movieId);
        if (allrating.isEmpty()) {
            return new ResponseEntity<>("未获取到评价", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allrating, HttpStatus.OK);
        }

    }

    /**
     * 用户点赞某条评价
     *
     * @param ratingId 评价的id
     * @return
     */
    @PostMapping("agreeOnRating")
    public ResponseEntity<Object> agreeOnRating(@RequestParam(value = "ratingId") Integer ratingId) {
        if (rateService.agreeOnRating(ratingId)) {
            return new ResponseEntity<>("点赞成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("点赞失败", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 分页获取某个评分的电影
     *
     * @param pageNumber 页码
     * @param pageSize   页大小
     * @param rating     评分
     * @return 电影列表
     */
    @GetMapping("getMoviesByRating")
    public ResponseEntity<Object> getMoviesByRating(@RequestParam(value = "pageNumber") int pageNumber,
                                                    @RequestParam(value = "pageSize") int pageSize,
                                                    @RequestParam(value = "rating") int rating) {
        IPage<MovieSimple> movies = rateService.getMoviesByRating(rating, pageNumber, pageSize);
        if (Objects.isNull(movies)) {
            return new ResponseEntity<>("获取失败", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
    }
}
