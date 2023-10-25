package br.ufpe.cin.monitorcloudcost.resource;

import br.ufpe.cin.monitorcloudcost.service.AwsCostExplorerApiService;
import br.ufpe.cin.monitorcloudcost.service.GcpCloudBillingApiService;
import br.ufpe.cin.monitorcloudcost.service.MicrosoftBillingApiService;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/costs")
public class CostResource {

    private final AwsCostExplorerApiService awsCostExplorerApiService;

    private final MicrosoftBillingApiService microsoftBillingApiService;

    private final GcpCloudBillingApiService gcpCloudBillingApiService;

    public CostResource(final AwsCostExplorerApiService awsCostExplorerApiService,
            final MicrosoftBillingApiService microsoftBillingApiService,
            final GcpCloudBillingApiService gcpCloudBillingApiService) {
        this.awsCostExplorerApiService = awsCostExplorerApiService;
        this.microsoftBillingApiService = microsoftBillingApiService;
        this.gcpCloudBillingApiService = gcpCloudBillingApiService;
    }

    @GetMapping("/used-clouds")
    public List<String> clouds() {
        return Arrays.asList("Amazon Web Services (AWS)", "Google Cloud Platform", "Microsoft Azure");
    }

    @GetMapping("/get-cost-and-usage")
    public String getCostAndUsage() {
        return awsCostExplorerApiService.getCostAndUsage();
    }

    @GetMapping("/get-microsoft-billing-periods")
    public String getBillingPeriodsMicrosoft() {
        return microsoftBillingApiService.getBillingPeriods();
    }

    @GetMapping("/get-microsoft-billing-usage-details")
    public String getUsageDetailsMicrosoft() {
        return microsoftBillingApiService.getUsageDetails();
    }

    @GetMapping("/get-microsoft-consumption")
    public String getConsumption() {
        return microsoftBillingApiService.getConsumption();
    }

    @GetMapping("/get-billing-info-google-cloud")
    public String getBillingInfoGoogleCloud() {
        return gcpCloudBillingApiService.getBillingInfo();
    }

    @GetMapping("/get-billing-account-google-cloud")
    public String getBillingAccount() {
        return gcpCloudBillingApiService.getBillingAccount();
    }

    @GetMapping("/test-big-query-google-cloud")
    public String testBigQuery() {
        return gcpCloudBillingApiService.bigQuery();
    }

}
