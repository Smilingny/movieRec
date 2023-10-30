package com.example.movierec.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("movie")
public class Movie {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private Integer duration;
    private Date released;
    private Float rating;
    private String poster;
    private String director;
    private String actors;
    private String plot;
}
