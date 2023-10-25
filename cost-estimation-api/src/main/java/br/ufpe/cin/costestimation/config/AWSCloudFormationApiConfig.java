package br.ufpe.cin.costestimation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudformation.CloudFormationClient;

@Configuration
public class AWSCloudFormationApiConfig {

    private final AwsCredentialsApi awsCredentialsApi;

    public AWSCloudFormationApiConfig(final AwsCredentialsApi awsCredentialsApi) {
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
    public CloudFormationClient awsCloudFormationClient() {
        final CloudFormationClient cloudFormationClient = CloudFormationClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(awsCredentialsProvider())
                .build();

        return cloudFormationClient;
    }

}
