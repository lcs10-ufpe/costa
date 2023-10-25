package br.ufpe.cin.budgeting.service;

import br.ufpe.cin.budgeting.exception.BudgetingNotFoundException;
import br.ufpe.cin.budgeting.mapper.BudgetingAnalysisMapper;
import br.ufpe.cin.budgeting.model.BudgetingEntity;
import br.ufpe.cin.budgeting.model.RunningEnum;
import br.ufpe.cin.budgeting.repository.Budgetings;
import br.ufpe.cin.budgeting.vo.BudgetingAnalysisVO;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BudgetingService {

    private final Budgetings budgetings;

    private final BudgetingAnalysisMapper budgetingAnalysisMapper;

    public BudgetingService(final Budgetings budgetings, final BudgetingAnalysisMapper budgetingAnalysisMapper) {
        this.budgetings = budgetings;
        this.budgetingAnalysisMapper = budgetingAnalysisMapper;
    }

    public Optional<BudgetingEntity> findFetchById(final Long id) {
        final BudgetingEntity budgeting = budgetings.findFetchById(id);

        if (budgeting == null) {
            throw new BudgetingNotFoundException();
        }

        return Optional.of(budgeting);
    }

    public Optional<BudgetingEntity> findById(final Long id) {
        return budgetings.findById(id)
                .map(budgeting -> Optional.of(budgeting))
                .orElseThrow(BudgetingNotFoundException::new);
    }

    public List<BudgetingEntity> all() {
        return budgetings.fetchAll();
    }

    public List<BudgetingAnalysisVO> budgetingsAnalysisVOS() {
        return budgetingAnalysisMapper.converterBudgetingEntityToBudgetingAnalysisVO(budgetings.fetchAll());
    }

    public void delete(final Long id) {
        budgetings.delete(findById(id).get());
    }

    public BudgetingEntity save(final BudgetingEntity budgeting) {
        budgeting.setCreation(LocalDate.now());
        budgeting.setRunningEnum(RunningEnum.NO);
        return budgetings.save(budgeting);
    }

    public void runScenario(final Long id, final String nameProject) {
        final BudgetingEntity budgeting = findById(id).get();

        final Optional<BudgetingEntity> budgetingRunning = budgetings.findByRunningEnumAndNameProject(RunningEnum.YES,
                nameProject);
        if (budgetingRunning.isPresent()) {
            budgetingRunning.get().setRunningEnum(RunningEnum.NO);
            budgetings.save(budgetingRunning.get());
        }

        budgeting.setRunningEnum(RunningEnum.YES);
        budgetings.save(budgeting);
    }

}
