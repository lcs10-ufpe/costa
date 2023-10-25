package br.ufpe.cin.executer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "BudgetingApiClient", url = "${base.url.budgeting.api}")
public interface BudgetingApiClient {

    @PutMapping("/budgetings/run/{id}/{nameProject}/scenario")
    void runScenario(@PathVariable("id") final Long id, @PathVariable("nameProject") final String nameProject);

}
