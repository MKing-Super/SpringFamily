package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("WebMvcConfigurer");
//        registry.addInterceptor(authInterceptor)
//                .addPathPatterns("/**") // 拦截所有路径
//                .excludePathPatterns("/error", "/static/**"); // 排除一些路径
    }

}
