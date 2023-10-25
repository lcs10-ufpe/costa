package br.ufpe.cin.budgeting.mapper;

import br.ufpe.cin.budgeting.model.BudgetingEntity;
import br.ufpe.cin.budgeting.model.CloudProviderEnum;
import br.ufpe.cin.budgeting.model.MicroServiceEntity;
import br.ufpe.cin.budgeting.vo.BudgetingAnalysisVO;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

@Component
public class BudgetingAnalysisMapper {

    public List<BudgetingAnalysisVO> converterBudgetingEntityToBudgetingAnalysisVO(
            final List<BudgetingEntity> budgetings) {

        final List<BudgetingAnalysisVO> budgetingAnalysisVOS = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(budgetings)) {

            budgetings.stream().forEach(budgeting -> {
                BudgetingAnalysisVO vo = BudgetingAnalysisVO.builder()
                        .id(budgeting.getId())
                        .name(budgeting.getName())
                        .totalCost(budgeting.getTotalCost())
                        .currencyEnum(budgeting.getCurrencyEnum())
                        .effectiveFrom(budgeting.getEffectiveFrom())
                        .effectiveTo(budgeting.getEffectiveTo())
                        .periodEnum(budgeting.getPeriodEnum())
                        .runningEnum(budgeting.getRunningEnum())
                        .cloudProviderEnum(findCloudProviderEnum(budgeting.getMicroServices()))
                        .nameProject(budgeting.getNameProject())
                        .build();

                budgetingAnalysisVOS.add(vo);
            });

        }

        return budgetingAnalysisVOS;
    }

    // TODO esse codigo vai ser atualizado futuramente - por enquanto vai ficar tudo na mesma cloud
    private CloudProviderEnum findCloudProviderEnum(final List<MicroServiceEntity> microServiceEntityList) {
        return microServiceEntityList.get(0).getCloud().getCloudProviderEnum();
    }

}
