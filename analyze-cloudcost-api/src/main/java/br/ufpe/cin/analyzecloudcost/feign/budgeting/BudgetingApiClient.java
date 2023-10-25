package br.ufpe.cin.analyzecloudcost.feign.budgeting;

import br.ufpe.cin.analyzecloudcost.vo.BudgetingAnalysisVO;
import java.util.List;

public interface BudgetingApiClient {

    List<BudgetingAnalysisVO> budgetingsAnalysis();

}
