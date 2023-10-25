package br.ufpe.cin.budgeting.resource;

import br.ufpe.cin.budgeting.model.BudgetingEntity;
import br.ufpe.cin.budgeting.service.BudgetingService;
import br.ufpe.cin.budgeting.vo.BudgetingAnalysisVO;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budgetings")
public class BudgetingResource {

    private final BudgetingService budgetingService;

    public BudgetingResource(final BudgetingService budgetingService) {
        this.budgetingService = budgetingService;
    }

    @GetMapping
    public List<BudgetingEntity> all() {
        return budgetingService.all();
    }

    @GetMapping("/analysis")
    public List<BudgetingAnalysisVO> budgetingsAnalysis() {
        return budgetingService.budgetingsAnalysisVOS();
    }

    @GetMapping("/{id}")
    public BudgetingEntity findById(@PathVariable("id") final Long id) {
        final Optional<BudgetingEntity> budgeting = budgetingService.findFetchById(id);
        return budgeting.get();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody BudgetingEntity budgeting) {
        budgetingService.save(budgeting);
    }

    @PutMapping("/run/{id}/{nameProject}/scenario")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void runScenario(@PathVariable("id") final Long id, @PathVariable("nameProject") final String nameProject) {
        budgetingService.runScenario(id, nameProject);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        budgetingService.delete(id);
    }

}
