package br.ufpe.cin.analyzecloudcost.feign.monitor;

import br.ufpe.cin.analyzecloudcost.vo.BillingResponseVO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "MonitorCloudCostApiClient", url = "${base.url.monitor.cloudcost.api}")
public interface MonitorCloudCostApi {

    @GetMapping("/billings/get-cost-and-usage")
    List<BillingResponseVO> getCostAndUsage();

}
