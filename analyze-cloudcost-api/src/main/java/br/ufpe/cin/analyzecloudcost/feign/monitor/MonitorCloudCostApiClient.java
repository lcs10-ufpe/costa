package br.ufpe.cin.analyzecloudcost.feign.monitor;

import br.ufpe.cin.analyzecloudcost.vo.BillingResponseVO;
import java.util.List;

public interface MonitorCloudCostApiClient {

    List<BillingResponseVO> getCostAndUsage();

}
