package com.repeta.airport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AirportMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirportMsApplication.class, args);
    }

}
