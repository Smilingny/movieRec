package com.example.movierec.config;

import com.example.movierec.interceptor.JWTInterceptor;
import com.example.movierec.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private RedisCache redisCache;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor(redisCache))
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/register","/v3", "/v3/");  // 放行登陆、注册请求
    }
}
