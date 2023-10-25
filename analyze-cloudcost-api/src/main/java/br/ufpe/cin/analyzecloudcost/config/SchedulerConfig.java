package br.ufpe.cin.analyzecloudcost.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan("br.ufpe.cin.analyzecloudcost.scheduler")
public class SchedulerConfig {
}
