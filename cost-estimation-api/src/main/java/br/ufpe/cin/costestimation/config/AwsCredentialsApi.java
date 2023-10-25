package br.ufpe.cin.costestimation.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class AwsCredentialsApi {

    @Value("${amazon.cost.estimation.accesskey}")
    private String accesskey;

    @Value("${amazon.cost.estimation.secretkey}")
    private String secretkey;

}
