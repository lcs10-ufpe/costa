package br.ufpe.cin.analyzecloudcost.feign.planner;

import br.ufpe.cin.analyzecloudcost.vo.MigrationCloudRequestVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "PlannerApiClient", url = "${base.url.planner.api}")
public interface PlannerApi {

    @PostMapping("/plans")
    void create(final MigrationCloudRequestVO migrationCloudRequestVO);

}
