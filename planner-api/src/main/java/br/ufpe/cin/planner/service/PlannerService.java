package br.ufpe.cin.planner.service;

import br.ufpe.cin.planner.feign.ExecutorApiClient;
import br.ufpe.cin.planner.vo.MigrationCloudRequestVO;
import br.ufpe.cin.planner.vo.PlanMigrationVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PlannerService {

    private final ExecutorApiClient executorApiClient;

    public PlannerService(final ExecutorApiClient executorApiClient) {
        this.executorApiClient = executorApiClient;
    }

    public void createPlanning(final MigrationCloudRequestVO migrationCloudRequestVO) {

        log.info(migrationCloudRequestVO.toString());

        final PlanMigrationVO planMigrationVO = PlanMigrationVO.builder()
                .nameProject(migrationCloudRequestVO.getNameProject())
                .deploy(migrationCloudRequestVO.getNewCloudProviderEnum())
                // .remove(migrationCloudRequestVO.getOldCloudProviderEnum())
                .update(migrationCloudRequestVO.getIdNewScenarioBudgetingRunning())
                .build();

        log.info(planMigrationVO.toString());

        try {
            executorApiClient.execute(planMigrationVO);
        } catch (final Exception e) {
            log.error(e.getMessage());
        }
    }

}
