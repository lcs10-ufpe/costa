package br.ufpe.cin.costestimation.client;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.devopsguru.DevOpsGuruClient;
import software.amazon.awssdk.services.devopsguru.model.*;

import java.util.List;

@Component
public class DevOpsGuruClientImpl {

    private final DevOpsGuruClient devOpsGuruClient;

    public DevOpsGuruClientImpl(final DevOpsGuruClient devOpsGuruClient) {
        this.devOpsGuruClient = devOpsGuruClient;
    }

    public StartCostEstimationResponse startCostEstimation(final List<String> stackNames) {

        final CloudFormationCostEstimationResourceCollectionFilter cloudFormation = CloudFormationCostEstimationResourceCollectionFilter.builder()
                .stackNames(stackNames)
                .build();

        final CostEstimationResourceCollectionFilter resourceCollection = CostEstimationResourceCollectionFilter.builder()
                .cloudFormation(cloudFormation)
                .build();

        final StartCostEstimationRequest start = StartCostEstimationRequest.builder()
                .resourceCollection(resourceCollection)
                .build();

        final StartCostEstimationResponse response = devOpsGuruClient.startCostEstimation(start);

        System.out.println("Status code DevOpsGuru.: " + response.toBuilder().sdkHttpResponse().statusCode());

        return response;
    }

    public void getCostEstimation() {

        GetCostEstimationResponse costEstimation = devOpsGuruClient.getCostEstimation(GetCostEstimationRequest.builder().build());

        if ("COMPLETED".equals(costEstimation.statusAsString())) {
            System.out.println("Finished.");
            System.out.println("totalCost.: " + costEstimation.totalCost());
        } else {
            System.out.println("Progress.");
        }
    }

}
