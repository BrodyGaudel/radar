package org.mounanga.radarcommandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class RadarCommandServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RadarCommandServiceApplication.class, args);
    }

}
