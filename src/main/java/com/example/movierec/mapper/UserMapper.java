package com.example.movierec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.movierec.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
