package br.ufpe.cin.monitorcloudcost.service.impl;

import br.ufpe.cin.monitorcloudcost.feign.MicrosoftBillingApiClient;
import br.ufpe.cin.monitorcloudcost.model.CloudProviderEnum;
import br.ufpe.cin.monitorcloudcost.model.CurrencyEnum;
import br.ufpe.cin.monitorcloudcost.service.MicrosoftBillingApiService;
import br.ufpe.cin.monitorcloudcost.vo.CloudCostVO;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class MicrosoftBillingApiServiceImpl implements MicrosoftBillingApiService {

    private final MicrosoftBillingApiClient microsoftBillingPeriodClient;

    public MicrosoftBillingApiServiceImpl(final MicrosoftBillingApiClient microsoftBillingPeriodClient) {
        this.microsoftBillingPeriodClient = microsoftBillingPeriodClient;
    }

    @Override
    public String getBillingPeriods() {
        return microsoftBillingPeriodClient.getBillingPeriods();
    }

    @Override
    public String getUsageDetails() {
        return microsoftBillingPeriodClient.getUsageDetails();
    }

    @Override
    public String getConsumption() {
        return microsoftBillingPeriodClient.getConsumption();
    }

    @Override
    public CloudCostVO testGetUsageDetails() {

        final String result = microsoftBillingPeriodClient.getUsageDetails();

        final CloudCostVO vo = new CloudCostVO();

        if (result.contains("{\"value\":[]}")) {
            vo.setCloudProviderEnum(CloudProviderEnum.MICROSOFT_AZURE);
            vo.setAmount(BigDecimal.ZERO);
            vo.setCurrencyEnum(CurrencyEnum.DOLAR);
        } else {
            // TODO realizar a implementacao quando tiver um retorno do custo
        }

        return vo;
    }

}
