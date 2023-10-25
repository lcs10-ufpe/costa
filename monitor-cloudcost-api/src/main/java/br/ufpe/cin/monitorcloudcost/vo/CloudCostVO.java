package br.ufpe.cin.monitorcloudcost.vo;

import br.ufpe.cin.monitorcloudcost.model.CloudProviderEnum;
import br.ufpe.cin.monitorcloudcost.model.CurrencyEnum;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CloudCostVO {
    private CloudProviderEnum cloudProviderEnum;
    private BigDecimal amount;
    private CurrencyEnum currencyEnum;
    // private String unit;
}
