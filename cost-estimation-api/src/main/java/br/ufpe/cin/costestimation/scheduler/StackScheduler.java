package br.ufpe.cin.costestimation.scheduler;

import br.ufpe.cin.costestimation.service.StackService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StackScheduler {

    private final StackService stackService;

    private StackScheduler(final StackService stackService) {
        this.stackService = stackService;
    }

    @Scheduled(fixedDelay = 120000, initialDelay = 60000)
    public void runningStack() {
        stackService.sendStackCloudFormation();
        stackService.startCostEstimationDevOpsGuru();
        stackService.getCostEstimationDevOpsGuru();
    }

}
