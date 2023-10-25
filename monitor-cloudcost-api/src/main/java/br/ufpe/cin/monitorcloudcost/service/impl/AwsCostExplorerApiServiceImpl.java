package br.ufpe.cin.monitorcloudcost.service.impl;

import br.ufpe.cin.monitorcloudcost.model.CloudProviderEnum;
import br.ufpe.cin.monitorcloudcost.model.CurrencyEnum;
import br.ufpe.cin.monitorcloudcost.service.AwsCostExplorerApiService;
import br.ufpe.cin.monitorcloudcost.vo.CloudCostVO;
import com.amazonaws.services.costexplorer.AWSCostExplorer;
import com.amazonaws.services.costexplorer.model.DateInterval;
import com.amazonaws.services.costexplorer.model.GetCostAndUsageRequest;
import com.amazonaws.services.costexplorer.model.GetCostAndUsageResult;
import com.amazonaws.services.costexplorer.model.MetricValue;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import org.springframework.stereotype.Service;

@Service
public class AwsCostExplorerApiServiceImpl implements AwsCostExplorerApiService {

    private final AWSCostExplorer awsCostExplorerClient;

    public AwsCostExplorerApiServiceImpl(final AWSCostExplorer awsCostExplorerClient) {
        this.awsCostExplorerClient = awsCostExplorerClient;
    }

    @Override
    public String getCostAndUsage() {
        final GetCostAndUsageRequest request = new GetCostAndUsageRequest();

        final LocalDate dateEnd = LocalDate.now();
        final LocalDate dateStart = dateEnd.minus(Period.ofDays(1));

        request.withTimePeriod(new DateInterval().withStart(dateStart.toString()).withEnd(dateEnd.toString()))
                .withGranularity("DAILY")
                .withMetrics("BlendedCost", "UnblendedCost", "UsageQuantity");

        final GetCostAndUsageResult result = awsCostExplorerClient.getCostAndUsage(request);
        return result.toString();
    }

    @Override
    public CloudCostVO testGetCostAndUsage() {
        final GetCostAndUsageRequest request = new GetCostAndUsageRequest();

        final LocalDate dateEnd = LocalDate.now();
        final LocalDate dateStart = dateEnd.minus(Period.ofDays(1));

        request.withTimePeriod(new DateInterval().withStart(dateStart.toString()).withEnd(dateEnd.toString()))
                .withGranularity("DAILY")
                .withMetrics("BlendedCost", "UnblendedCost", "UsageQuantity");

        final GetCostAndUsageResult result = awsCostExplorerClient.getCostAndUsage(request);

        final CloudCostVO vo = new CloudCostVO();

        result.getResultsByTime().stream().forEach(resultByTime -> {
            MetricValue metricValue = resultByTime.getTotal().get("BlendedCost");
            vo.setCloudProviderEnum(CloudProviderEnum.AMAZON_WEB_SERVICES);
            final String amount = metricValue.getAmount();
            vo.setAmount(new BigDecimal(amount));
            vo.setCurrencyEnum(CurrencyEnum.DOLAR);
            // vo.setUnit(metricValue.getUnit());
        });

        return vo;
    }

}
