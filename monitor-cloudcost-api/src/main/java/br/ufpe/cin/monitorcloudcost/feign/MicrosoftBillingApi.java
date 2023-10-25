package br.ufpe.cin.monitorcloudcost.feign;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "MicrosoftBillingApiClient", url = "${base.url.microsof.billing.api}")
public interface MicrosoftBillingApi {

    @GetMapping("/Microsoft.Billing/billingPeriods?api-version=2017-04-24-preview")
    @Headers("Content-Type: application/json")
    String getBillingPeriods(@RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/Microsoft.Consumption/usageDetails?api-version=2018-03-31&$expand=properties/additionalProperties")
    @Headers("Content-Type: application/json")
    String getUsageDetails(@RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/Microsoft.Commerce/UsageAggregates?api-version=2015-06-01-preview&reportedStartTime=2020-06-26T00%3a00%3a00%2b00%3a00&reportedEndTime=2020-06-27T00%3a00%3a00%2b00%3a00&aggregationGranularity=Daily&showDetails=true")
    @Headers("Content-Type: application/json")
    String getConsumption(@RequestHeader(value = "Authorization") String authorizationHeader);

}
