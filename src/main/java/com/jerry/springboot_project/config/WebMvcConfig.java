package com.jerry.springboot_project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * springMVC配置
 *
 * @author Jerry 2024.10.13
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 用户拦截器
        registry.addInterceptor(new UserInterceptor())
                // 需要拦截的请求
                .addPathPatterns("/**")
                // 需要放行的请求
                .excludePathPatterns("/user/login","/user/register","/user/sendCode","/info/**")
                // 添加swagger-ui的放行路径
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","/doc.html/**");
    }



}