package com.square.health.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        if (!registry.hasMappingForPattern("/resources/static/**")) {
//            registry.addResourceHandler("/static/**")
//                    .addResourceLocations("/static/**");
////        }
//    }
}
