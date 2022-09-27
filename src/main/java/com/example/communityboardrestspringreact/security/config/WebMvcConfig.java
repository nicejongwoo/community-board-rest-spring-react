package com.example.communityboardrestspringreact.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
//                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method","Access-Control-Request-Headers")
//                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
