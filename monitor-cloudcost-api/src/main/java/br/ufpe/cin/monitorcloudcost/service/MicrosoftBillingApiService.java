package br.ufpe.cin.monitorcloudcost.service;

import br.ufpe.cin.monitorcloudcost.vo.CloudCostVO;

public interface MicrosoftBillingApiService {

    String getBillingPeriods();

    String getUsageDetails();

    String getConsumption();

    CloudCostVO testGetUsageDetails();

}
