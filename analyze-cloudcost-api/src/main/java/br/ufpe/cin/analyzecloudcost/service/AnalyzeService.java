package br.ufpe.cin.analyzecloudcost.service;

import br.ufpe.cin.analyzecloudcost.feign.budgeting.BudgetingApiClient;
import br.ufpe.cin.analyzecloudcost.feign.monitor.MonitorCloudCostApiClient;
import br.ufpe.cin.analyzecloudcost.feign.planner.PlannerApiClient;
import br.ufpe.cin.analyzecloudcost.model.CloudProviderEnum;
import br.ufpe.cin.analyzecloudcost.model.RunningEnum;
import br.ufpe.cin.analyzecloudcost.vo.BillingResponseVO;
import br.ufpe.cin.analyzecloudcost.vo.BudgetingAnalysisVO;
import br.ufpe.cin.analyzecloudcost.vo.MigrationCloudRequestVO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AnalyzeService {

    private final MonitorCloudCostApiClient monitorCloudCostApiClient;

    private final BudgetingApiClient budgetingApiClient;

    private final PlannerApiClient plannerApiClient;

    public AnalyzeService(final MonitorCloudCostApiClient monitorCloudCostApiClient,
            final BudgetingApiClient budgetingApiClient,
            final PlannerApiClient plannerApiClient) {
        this.monitorCloudCostApiClient = monitorCloudCostApiClient;
        this.budgetingApiClient = budgetingApiClient;
        this.plannerApiClient = plannerApiClient;
    }

    public void analyzeBudgetings() {

        final List<BillingResponseVO> billings = monitorCloudCostApiClient.getCostAndUsage();
        final List<BudgetingAnalysisVO> budgetings = budgetingApiClient.budgetingsAnalysis();

        final Map<String, List<BudgetingAnalysisVO>> groupBudgetings = groupByNameProjectBudgeting(budgetings);

        groupBudgetings.values().stream().forEach(budgetingAnalysisVOS -> {

            BudgetingAnalysisVO budgetingScenarioRunning = budgetingAnalysisVOS.stream()
                    .filter(budgetingFilter -> RunningEnum.YES.equals(budgetingFilter.getRunningEnum()))
                    .findAny()
                    .orElse(null);

            final BillingResponseVO billingResponseVO = billings.stream()
                    .filter(billing -> budgetingScenarioRunning.getCloudProviderEnum()
                            .equals(billing.getCloudProviderEnum()))
                    .findAny()
                    .orElse(null);

            final boolean isEqual = billingResponseVO.getTotalCost()
                    .compareTo(budgetingScenarioRunning.getTotalCost()) == 0;
            final boolean isGreaterThan = billingResponseVO.getTotalCost()
                    .compareTo(budgetingScenarioRunning.getTotalCost()) == 1;
            final boolean isLesserThan = billingResponseVO.getTotalCost()
                    .compareTo(budgetingScenarioRunning.getTotalCost()) == -1;

            if (isEqual) {
                log.info("Nao realizar a migracao");
            } else if (isLesserThan) {
                log.info("Nao realizar a migracao");
            } else if (isGreaterThan) {
                log.info("Realizar a migracao");
                final List<BudgetingAnalysisVO> newListBudgeting = budgetingAnalysisVOS.stream()
                        .filter(budgetingFilter -> !budgetingScenarioRunning.getId().equals(budgetingFilter.getId()))
                        .collect(Collectors.toList());

                newListBudgeting.sort(Comparator.comparing(BudgetingAnalysisVO::getTotalCost));
                performMigration(newListBudgeting.get(0), budgetingScenarioRunning.getCloudProviderEnum());
            }

        });

    }

    private Map<String, List<BudgetingAnalysisVO>> groupByNameProjectBudgeting(
            final List<BudgetingAnalysisVO> budgetings) {
        final Map<String, List<BudgetingAnalysisVO>> groupBudgetings = new HashMap<>();

        if (CollectionUtils.isNotEmpty(budgetings)) {
            budgetings.stream().forEach(budgeting -> {

                if (groupBudgetings.containsKey(budgeting.getNameProject())) {
                    final List<BudgetingAnalysisVO> budgetingAnalysisVOS = groupBudgetings
                            .get(budgeting.getNameProject());
                    budgetingAnalysisVOS.add(budgeting);
                } else {
                    final List<BudgetingAnalysisVO> newBudgetings = new ArrayList<>();
                    newBudgetings.add(budgeting);
                    groupBudgetings.put(budgeting.getNameProject(), newBudgetings);
                }

            });
        }

        return groupBudgetings;
    }

    private void performMigration(final BudgetingAnalysisVO budgeting, final CloudProviderEnum oldCloudProviderEnum) {
        final MigrationCloudRequestVO migrationCloudRequestVO = MigrationCloudRequestVO.builder()
                .nameProject(budgeting.getNameProject())
                .newCloudProviderEnum(budgeting.getCloudProviderEnum())
                .oldCloudProviderEnum(oldCloudProviderEnum)
                .idNewScenarioBudgetingRunning(budgeting.getId())
                .build();

        log.info(migrationCloudRequestVO.toString());

        plannerApiClient.create(migrationCloudRequestVO);
    }

    public void obtainRandomCloud() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        final LocalDateTime now = LocalDateTime.now();

        log.info("init data hora " + now.format(formatter));

        final String nameProject = "Beerstore";

        CloudProviderEnum cloudProviderEnum = getProviderRandom();

        final CloudProviderEnum cloudProviderRunning = getBudgetingProviderRunning(nameProject);

        while (cloudProviderEnum.equals(cloudProviderRunning)) {
            cloudProviderEnum = getProviderRandom();
        }

        final MigrationCloudRequestVO migrationCloudRequestVO = MigrationCloudRequestVO.builder()
                .nameProject(nameProject)
                .newCloudProviderEnum(cloudProviderEnum)
                // .oldCloudProviderEnum(oldCloudProviderEnum)
                .idNewScenarioBudgetingRunning(getIdNewScenarioBudgetingRunning(nameProject, cloudProviderEnum))
                .build();

        log.info(migrationCloudRequestVO.toString());

        try {
            plannerApiClient.create(migrationCloudRequestVO);
        } catch (final Exception e) {
            log.error(e.getMessage());
        }
    }

    private CloudProviderEnum getProviderRandom() {
        final Integer number = (int) (Math.random() * ((3 - 1) + 1)) + 1;

        return CloudProviderEnum.getCloudProviderEnum(number);
    }

    private CloudProviderEnum getBudgetingProviderRunning(final String nameProject) {
        final List<BudgetingAnalysisVO> budgetings = budgetingApiClient.budgetingsAnalysis();

        final BudgetingAnalysisVO budgetingAnalysisVO = budgetings.stream()
                .filter(budgeting -> RunningEnum.YES.equals(budgeting.getRunningEnum())
                        && nameProject.equals(budgeting.getNameProject()))
                .findFirst().get();

        return budgetingAnalysisVO.getCloudProviderEnum();
    }

    private Long getIdNewScenarioBudgetingRunning(final String nameProject,
            final CloudProviderEnum newCloudProviderEnum) {
        final List<BudgetingAnalysisVO> budgetings = budgetingApiClient.budgetingsAnalysis();

        final BudgetingAnalysisVO budgetingAnalysisVO = budgetings.stream()
                .filter(budgeting -> newCloudProviderEnum.equals(budgeting.getCloudProviderEnum())
                        && nameProject.equals(budgeting.getNameProject()))
                .findFirst().get();

        return budgetingAnalysisVO.getId();
    }

}
