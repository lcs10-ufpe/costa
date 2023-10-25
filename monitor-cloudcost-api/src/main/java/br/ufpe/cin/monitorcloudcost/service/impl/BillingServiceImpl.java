package br.ufpe.cin.monitorcloudcost.service.impl;

import br.ufpe.cin.monitorcloudcost.mapper.BillingMapper;
import br.ufpe.cin.monitorcloudcost.model.BillingEntity;
import br.ufpe.cin.monitorcloudcost.repository.Billings;
import br.ufpe.cin.monitorcloudcost.service.AwsCostExplorerApiService;
import br.ufpe.cin.monitorcloudcost.service.BillingService;
import br.ufpe.cin.monitorcloudcost.service.GcpCloudBillingApiService;
import br.ufpe.cin.monitorcloudcost.service.MicrosoftBillingApiService;
import br.ufpe.cin.monitorcloudcost.vo.CloudCostVO;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class BillingServiceImpl implements BillingService {

    private final Billings billings;

    private final BillingMapper billingMapper;

    private final AwsCostExplorerApiService awsCostExplorerApiService;

    private final MicrosoftBillingApiService microsoftBillingApiService;

    private final GcpCloudBillingApiService gcpCloudBillingApiService;

    public BillingServiceImpl(final Billings billings, final BillingMapper billingMapper,
            final AwsCostExplorerApiService awsCostExplorerApiService,
            final MicrosoftBillingApiService microsoftBillingApiService,
            final GcpCloudBillingApiService gcpCloudBillingApiService) {
        this.billings = billings;
        this.billingMapper = billingMapper;
        this.awsCostExplorerApiService = awsCostExplorerApiService;
        this.microsoftBillingApiService = microsoftBillingApiService;
        this.gcpCloudBillingApiService = gcpCloudBillingApiService;
    }

    @Override
    public void obtainBillingCloud() {
        final List<CloudCostVO> costs = new ArrayList<>();
        final CloudCostVO cloudAWS = awsCostExplorerApiService.testGetCostAndUsage();
        costs.add(cloudAWS);
        log.info("AWS");
        log.info(cloudAWS.toString());

        final CloudCostVO cloudAzure = microsoftBillingApiService.testGetUsageDetails();
        costs.add(cloudAzure);
        log.info("Azure");
        log.info(cloudAzure.toString());

        final CloudCostVO cloudGCP = gcpCloudBillingApiService.testBigQuery();
        costs.add(cloudGCP);
        log.info("GCP");
        log.info(cloudGCP.toString());

        final List<BillingEntity> billingList = billingMapper.mapCloudCostVOtoBillingEntity(costs);
        save(billingList);
    }

    public void save(final List<BillingEntity> billingList) {
        billings.saveAll(billingList);
    }

    @Override
    public List<BillingEntity> all() {
        return billings.findAll();
    }

}
