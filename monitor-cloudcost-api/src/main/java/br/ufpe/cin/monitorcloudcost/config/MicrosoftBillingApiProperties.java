package br.ufpe.cin.monitorcloudcost.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class MicrosoftBillingApiProperties {

    @Value("${microsoft.azure.billing.api.activedirectory.tenant-id}")
    private String tenantId;

    @Value("${microsoft.azure.billing.api.azure.client-id}")
    private String clientId;

    @Value("${microsoft.azure.billing.api.azure.client.secret}")
    private String secretKey;

}
