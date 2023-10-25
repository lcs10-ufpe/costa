package br.ufpe.cin.analyzecloudcost.feign.budgeting;

import br.ufpe.cin.analyzecloudcost.vo.BudgetingAnalysisVO;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BudgetingApiClientImpl implements BudgetingApiClient {

    private final BudgetingApi budgetingApi;

    public BudgetingApiClientImpl(final BudgetingApi budgetingApi) {
        this.budgetingApi = budgetingApi;
    }

    public List<BudgetingAnalysisVO> budgetingsAnalysis() {
        return budgetingApi.budgetingsAnalysis();
    }

}
