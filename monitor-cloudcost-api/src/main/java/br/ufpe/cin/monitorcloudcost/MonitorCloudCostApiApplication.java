package br.ufpe.cin.monitorcloudcost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MonitorCloudCostApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorCloudCostApiApplication.class, args);
    }

}
