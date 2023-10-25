package br.ufpe.cin.analyzecloudcost.feign.monitor;

import br.ufpe.cin.analyzecloudcost.vo.BillingResponseVO;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MonitorCloudCostApiClientImpl implements MonitorCloudCostApiClient {

    private final MonitorCloudCostApi monitorCloudCostApi;

    public MonitorCloudCostApiClientImpl(final MonitorCloudCostApi monitorCloudCostApi) {
        this.monitorCloudCostApi = monitorCloudCostApi;
    }

    public List<BillingResponseVO> getCostAndUsage() {
        return monitorCloudCostApi.getCostAndUsage();
    }

}
