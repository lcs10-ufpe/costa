package br.ufpe.cin.analyzecloudcost.feign.planner;

import br.ufpe.cin.analyzecloudcost.vo.MigrationCloudRequestVO;

public interface PlannerApiClient {

    void create(final MigrationCloudRequestVO migrationCloudRequestVO);

}
