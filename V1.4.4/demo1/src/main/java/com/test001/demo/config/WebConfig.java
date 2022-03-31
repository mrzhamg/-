package com.test001.demo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//配置全局跨域
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")//允许访问全部路径
                .allowedOrigins("http://127.0.0.1:8080/","http://localhost:8080/","http://192.168.31.39:8080/")  //只允许8080端口访问
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")//请求方式
                .allowCredentials(true)//允许发送token
                .maxAge(3600)//请求时长
                .allowedHeaders("*");
    }
}
