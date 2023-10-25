package br.ufpe.cin.monitorcloudcost.mapper;

import br.ufpe.cin.monitorcloudcost.model.BillingEntity;
import br.ufpe.cin.monitorcloudcost.vo.CloudCostVO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BillingMapper {

    public List<BillingEntity> mapCloudCostVOtoBillingEntity(final List<CloudCostVO> costs) {

        final List<BillingEntity> billings = new ArrayList<>();

        costs.stream().forEach(cost -> billings.add(BillingEntity.builder()
                .cloudProviderEnum(cost.getCloudProviderEnum())
                .currencyEnum(cost.getCurrencyEnum())
                .totalCost(cost.getAmount())
                .build()));

        return billings;
    }

}
