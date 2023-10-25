package br.ufpe.cin.executer.scheduler;

import br.ufpe.cin.executer.service.RemoveDeployService;
import java.io.IOException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RemoveDeployScheduler {

    private final RemoveDeployService removeDeployService;

    public RemoveDeployScheduler(final RemoveDeployService removeDeployService) {
        this.removeDeployService = removeDeployService;
    }

    @Scheduled(fixedDelay = 60000, initialDelay = 60000)
    public void scheduleRemoveDepoy() throws IOException {
        removeDeployService.executeRemoveDeploy();
    }

}
