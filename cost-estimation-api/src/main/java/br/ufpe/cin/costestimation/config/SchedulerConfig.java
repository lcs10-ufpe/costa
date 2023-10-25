package br.ufpe.cin.costestimation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan("br.ufpe.cin.costestimation.scheduler")
public class SchedulerConfig {
}
