package com.example.movierec.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("recommend")
public class Recommend {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer movie;
    private Integer user;
    private Date date;
}
