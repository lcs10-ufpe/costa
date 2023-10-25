package br.ufpe.cin.planner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PlannerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlannerApiApplication.class, args);
    }

}
