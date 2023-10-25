package br.ufpe.cin.monitorcloudcost.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan("br.ufpe.cin.monitorcloudcost.scheduler")
public class SchedulerConfig {
}
