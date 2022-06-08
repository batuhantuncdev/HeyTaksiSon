package com.heytaksi.heytaksibackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HeytaksibackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeytaksibackendApplication.class, args);
    }

}
