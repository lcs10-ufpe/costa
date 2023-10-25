package br.ufpe.cin.monitorcloudcost.scheduler;

import br.ufpe.cin.monitorcloudcost.service.BillingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ObtainBillingScheduler {

    private final BillingService billingService;

    public ObtainBillingScheduler(final BillingService billingService) {
        this.billingService = billingService;
    }

    @Scheduled(fixedDelay = 120000, initialDelay = 60000)
    public void scheduleObtainResult() {
        billingService.obtainBillingCloud();
    }

}
