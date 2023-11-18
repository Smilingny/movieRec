package com.example.movierec.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("user")
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String account;
    private String email;
    private Date birthday;
//    @JsonIgnore
    private String password;
    private Boolean sex;

    public User(String name, String account, String password, Boolean sex) {
        this.name=name;
        this.account=account;
        this.password=password;
        this.sex=sex;
    }

}
