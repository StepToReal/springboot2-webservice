package com.example.springboot.config;

import com.example.springboot.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //HandlerMethodArgumentResolver 은 WebMvcConfigurer 의 addArgumentResolvers 를 통해 추가해야 한다.
        //springframework 첫 시작 시 동작한다.
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
