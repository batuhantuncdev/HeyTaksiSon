package com.heytaksi.heytaksibackend.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class CorsConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //allows all endpoints replaces @CrossOrigin annotation
        registry.addMapping("/**")
                .allowedMethods("*")//allows all methods like GET or POST
                .allowedOrigins("*")//allows all origins like localhost
                .allowedHeaders("*")//allows all headers
                .allowCredentials( true )
                .allowedHeaders( "Authorization" )
                .maxAge( 3600 );
    }

}