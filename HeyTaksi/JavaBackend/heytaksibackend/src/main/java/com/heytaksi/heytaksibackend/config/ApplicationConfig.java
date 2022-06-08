package com.heytaksi.heytaksibackend.config;

import com.heytaksi.heytaksibackend.settings.HeyTaksiSettings;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@EnableConfigurationProperties(HeyTaksiSettings.class)
public class ApplicationConfig {
    private final HeyTaksiSettings settings;

    public ApplicationConfig(HeyTaksiSettings settings) {
        this.settings = settings;
    }

    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }

}
