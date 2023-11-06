package com.example.movierec.controller;

import com.example.movierec.service.RateService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/")
public class RateController {
    @Autowired
    private RateService rateService;

    /**
     * 评价电影
     * @param movieId 电影id
     * @param score 评分
     * @param comment 评价
     * @return 评价结果
     */
    @PutMapping("ratingMovie")
    public ResponseEntity<Object> ratingMovie(@RequestParam(value = "movieId") Integer movieId,
                                              @RequestParam(value = "rate") Double score,
                                              @RequestParam(value = "comment") String comment,
                                              HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("id");
        if (rateService.ratingMovie(userId,movieId,score,comment)) {
            return new ResponseEntity<>("评价成功", HttpStatus.OK);
        }
        return new ResponseEntity<>("评价失败", HttpStatus.BAD_REQUEST);
    }
}
