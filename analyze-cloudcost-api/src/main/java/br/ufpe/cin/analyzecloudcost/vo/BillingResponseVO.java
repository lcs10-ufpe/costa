package br.ufpe.cin.analyzecloudcost.vo;

import br.ufpe.cin.analyzecloudcost.model.CloudProviderEnum;
import br.ufpe.cin.analyzecloudcost.model.CurrencyEnum;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class BillingResponseVO {
    private CloudProviderEnum cloudProviderEnum;
    private BigDecimal totalCost;
    private CurrencyEnum currencyEnum;
    private String tag;
}
