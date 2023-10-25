package br.ufpe.cin.analyzecloudcost.scheduler;

import br.ufpe.cin.analyzecloudcost.service.AnalyzeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AnalyzeMigrationScheduler {

    private final AnalyzeService analyzeService;

    public AnalyzeMigrationScheduler(final AnalyzeService analyzeService) {
        this.analyzeService = analyzeService;
    }

    // @Scheduled(fixedDelay = 120000, initialDelay = 60000)
    // public void scheduleObtainResult() {
    // analyzeService.analyzeBudgetings();
    // }

    @Scheduled(fixedDelay = 1200000, initialDelay = 1200000)
    public void scheduleRandomCloud() {
        analyzeService.obtainRandomCloud();
    }

}
