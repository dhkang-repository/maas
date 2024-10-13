package com.xmass.cloud.infrastructure.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해 적용
                .allowedOriginPatterns("*") // 허용할 도메인 패턴
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")            // 허용할 HTTP 메서드
                .allowedHeaders("*")     // 허용할 헤더
                .allowCredentials(false);                                              // 자격 증명 허용
    }
}