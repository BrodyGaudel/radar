package org.mounanga.radarservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RadarServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RadarServiceApplication.class, args);
    }

}
