package br.ufpe.cin.executer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan("br.ufpe.cin.executer.scheduler")
public class SchedulerConfig {
}
