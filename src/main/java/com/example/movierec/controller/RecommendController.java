package com.example.movierec.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.movierec.dto.MovieSimple;
import com.example.movierec.service.RecommendService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/")
public class RecommendController {

    private final RecommendService recommendService;

    public RecommendController(RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    /**
     * 分页获取推荐电影
     * @param pageNumber 页码
     * @param pageSize 分页大小
     * @param request 包含用户id
     * @return 推荐电影列表
     */
    @GetMapping("getRecommend")
    public ResponseEntity<Object> getRecommend(@RequestParam(value = "pageNumber") int pageNumber,
                                               @RequestParam(value = "pageSize") int pageSize,
                                               HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("id");
        IPage<MovieSimple> recommend = recommendService.getRecommend(pageNumber, pageSize, userId);
        if (Objects.isNull(recommend)) {
            return new ResponseEntity<>("获取失败", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(recommend, HttpStatus.OK);
        }
    }

}
