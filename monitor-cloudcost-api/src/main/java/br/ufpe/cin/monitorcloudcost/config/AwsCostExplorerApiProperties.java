package br.ufpe.cin.monitorcloudcost.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class AwsCostExplorerApiProperties {

    @Value("${amazon.cost.explorer.accesskey}")
    private String accesskey;

    @Value("${amazon.cost.explorer.secretkey}")
    private String secretkey;

}
