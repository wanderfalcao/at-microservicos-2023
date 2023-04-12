package br.com.infnet.administratorservice;

import io.prometheus.client.Counter;
import io.prometheus.client.exporter.HTTPServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import javax.sql.DataSource;
import java.io.IOException;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories(basePackages = "br.com.infnet.administratorservice.repository")
public class AdministratorServiceApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(AdministratorServiceApplication.class, args);
    }

}
