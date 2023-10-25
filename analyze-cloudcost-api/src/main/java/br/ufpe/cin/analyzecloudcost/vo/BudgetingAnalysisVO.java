package br.ufpe.cin.analyzecloudcost.vo;

import br.ufpe.cin.analyzecloudcost.model.CloudProviderEnum;
import br.ufpe.cin.analyzecloudcost.model.CurrencyEnum;
import br.ufpe.cin.analyzecloudcost.model.PeriodEnum;
import br.ufpe.cin.analyzecloudcost.model.RunningEnum;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class BudgetingAnalysisVO {
    private Long id;
    private String name;
    private BigDecimal totalCost;
    private CurrencyEnum currencyEnum;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private PeriodEnum periodEnum;
    private RunningEnum runningEnum;
    private CloudProviderEnum cloudProviderEnum;
    private String nameProject;
}
