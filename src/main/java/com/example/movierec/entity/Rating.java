package com.example.movierec.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("rating")
@NoArgsConstructor
public class Rating {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer user;
    private Integer movie;
    private Date time;
    private String comment;
    private Double score;
    private Integer agree = 0;

    public Rating(Integer user,Integer movie,Double score,String comment){
        this.user = user;
        this.movie = movie;
        this.score = score;
        this.comment = comment;
        this.time = new Date();
    }
}
