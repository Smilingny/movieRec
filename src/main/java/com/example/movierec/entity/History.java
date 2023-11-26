package com.example.movierec.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("history")
@NoArgsConstructor
public class History {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer user;
    private Integer movie;
    private Date time;

    public History(Integer user,Integer movie){
        this.user = user;
        this.movie = movie;
        this.time = new Date();
    }
}
