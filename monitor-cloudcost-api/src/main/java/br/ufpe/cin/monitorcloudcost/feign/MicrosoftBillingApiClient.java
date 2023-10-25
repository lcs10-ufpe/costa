package br.ufpe.cin.monitorcloudcost.feign;

public interface MicrosoftBillingApiClient {

    int DEFAULT_SUCCESS_RATE = 70;

    int DEFAULT_AVERAGE_RESPONSE_TIME = 1500;

    String getBillingPeriods();

    String getUsageDetails();

    String getConsumption();
}
