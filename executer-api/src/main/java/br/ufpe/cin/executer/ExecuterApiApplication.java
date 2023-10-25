package br.ufpe.cin.executer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ExecuterApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExecuterApiApplication.class, args);
    }

}
