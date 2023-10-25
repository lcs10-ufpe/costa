package br.ufpe.cin.analyzecloudcost.feign.planner;

import br.ufpe.cin.analyzecloudcost.vo.MigrationCloudRequestVO;
import org.springframework.stereotype.Component;

@Component
public class PlannerApiClientImpl implements PlannerApiClient {

    private final PlannerApi plannerApi;

    public PlannerApiClientImpl(final PlannerApi plannerApi) {
        this.plannerApi = plannerApi;
    }

    public void create(final MigrationCloudRequestVO migrationCloudRequestVO) {
        plannerApi.create(migrationCloudRequestVO);
    }

}
