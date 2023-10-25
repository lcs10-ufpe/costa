package br.ufpe.cin.monitorcloudcost.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.costexplorer.AWSCostExplorer;
import com.amazonaws.services.costexplorer.AWSCostExplorerClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsCostExplorerApiConfig {

    private final AwsCostExplorerApiProperties awsCostExplorerApiProperties;

    public AwsCostExplorerApiConfig(final AwsCostExplorerApiProperties awsCostExplorerApiProperties) {
        this.awsCostExplorerApiProperties = awsCostExplorerApiProperties;
    }

    @Bean
    public BasicAWSCredentials basicAWSCredentials() {
        final BasicAWSCredentials sessionCredentials = new BasicAWSCredentials(
                awsCostExplorerApiProperties.getAccesskey(),
                awsCostExplorerApiProperties.getSecretkey());
        return sessionCredentials;
    }

    @Bean(destroyMethod = "shutdown")
    public AWSCostExplorer awsCostExplorer() {
        final AWSCostExplorerClientBuilder builder = AWSCostExplorerClientBuilder.standard();
        final AWSCostExplorer awsCostExplorerClient = builder
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials()))
                .withRegion(Regions.US_EAST_1).build();
        return awsCostExplorerClient;
    }

}
