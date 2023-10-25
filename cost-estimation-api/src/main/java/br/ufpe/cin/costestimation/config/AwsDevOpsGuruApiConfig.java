package br.ufpe.cin.costestimation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.devopsguru.DevOpsGuruClient;

@Configuration
public class AwsDevOpsGuruApiConfig {

    private final AwsCredentialsApi awsCredentialsApi;

    public AwsDevOpsGuruApiConfig(final AwsCredentialsApi awsCredentialsApi) {
        this.awsCredentialsApi = awsCredentialsApi;
    }

    @Bean
    public AwsCredentialsProvider awsCredentialsProvider() {
        return () -> new software.amazon.awssdk.auth.credentials.AwsCredentials() {
            @Override
            public String accessKeyId() {
                return awsCredentialsApi.getAccesskey();
            }

            @Override
            public String secretAccessKey() {
                return awsCredentialsApi.getSecretkey();
            }
        };
    }

    @Bean
    public DevOpsGuruClient awsDevOpsGuruClient() {
        final DevOpsGuruClient devOpsGuruClient = DevOpsGuruClient.builder()
                .credentialsProvider(awsCredentialsProvider())
                .region(Region.US_EAST_1)
                .build();
        return devOpsGuruClient;
    }

}
