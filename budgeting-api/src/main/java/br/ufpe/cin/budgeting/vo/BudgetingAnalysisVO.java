package br.ufpe.cin.budgeting.vo;

import br.ufpe.cin.budgeting.model.CloudProviderEnum;
import br.ufpe.cin.budgeting.model.CurrencyEnum;
import br.ufpe.cin.budgeting.model.PeriodEnum;
import br.ufpe.cin.budgeting.model.RunningEnum;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
