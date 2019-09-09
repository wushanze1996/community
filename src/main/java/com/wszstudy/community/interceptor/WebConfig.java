package com.wszstudy.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration

public class WebConfig implements WebMvcConfigurer {
@Autowired//这里需要将这个原本（new SessionInterceptor()）注入，而不能直接new，也就是刚才的@Service的那个实现类
private SessionInterceptor sessionInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor());
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").excludePathPatterns("/static/*");

    }
}
