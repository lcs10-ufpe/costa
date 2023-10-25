package br.ufpe.cin.analyzecloudcost.feign.budgeting;

import br.ufpe.cin.analyzecloudcost.vo.BudgetingAnalysisVO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "BudgetingApiClient", url = "${base.url.budgeting.api}")
public interface BudgetingApi {

    @GetMapping("/budgetings/analysis")
    List<BudgetingAnalysisVO> budgetingsAnalysis();

}
