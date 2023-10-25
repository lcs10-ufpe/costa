package br.ufpe.cin.planner.feign;

import br.ufpe.cin.planner.vo.PlanMigrationVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ExecutorApiClient", url = "${base.url.executer.api}")
public interface ExecutorApiClient {

    @PostMapping("/executors/plan")
    void execute(final PlanMigrationVO planMigrationVO);

}
