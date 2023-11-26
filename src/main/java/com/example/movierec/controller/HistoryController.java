package com.example.movierec.controller;

import com.example.movierec.service.HistoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HistoryController {
    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }


    /**
     * 用户点击电影，插入历史记录
     *
     * @param movieId 电影id
     */
    @PostMapping("clickMovie")
    public ResponseEntity<Object> clickMovie(@RequestParam(value = "movieId") Integer movieId,
                                             HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("id");
        try {
            historyService.clickMovie(userId, movieId);
            return ResponseEntity.ok().body("ok");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 获取历史记录数量
     * @param request 用户id
     * @return 数量列表
     */
    @GetMapping("historyCounts")
    public ResponseEntity<Object> getHistoryCounts(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("id");
        try {
            return ResponseEntity.ok().body(historyService.getHistoryCounts(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 获取电影历史记录数量
     * @param movieId 电影id
     * @return 数量列表
     */
    @GetMapping("movieHistoryCounts")
    public ResponseEntity<Object> getMovieHistoryCounts(@RequestParam(value = "movieId") Integer movieId) {
        try {
            return ResponseEntity.ok().body(historyService.getMovieHistoryCounts(movieId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
