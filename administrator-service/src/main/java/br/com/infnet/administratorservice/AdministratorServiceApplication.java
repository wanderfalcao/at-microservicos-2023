package br.com.infnet.administratorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories(basePackages = "br.com.infnet.administratorservice.repository")
public class AdministratorServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdministratorServiceApplication.class, args);
    }

}
