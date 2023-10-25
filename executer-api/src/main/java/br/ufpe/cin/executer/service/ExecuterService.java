package br.ufpe.cin.executer.service;

import br.ufpe.cin.executer.exception.ExecutorNotFoundException;
import br.ufpe.cin.executer.feign.BudgetingApiClient;
import br.ufpe.cin.executer.jenkins.JenkinsApiClient;
import br.ufpe.cin.executer.mapper.ExecutorMapper;
import br.ufpe.cin.executer.model.ActionEnum;
import br.ufpe.cin.executer.model.ExecutorEntity;
import br.ufpe.cin.executer.model.ExperimentEntity;
import br.ufpe.cin.executer.model.RemoveDeployEntity;
import br.ufpe.cin.executer.model.RunningEnum;
import br.ufpe.cin.executer.repository.Executors;
import br.ufpe.cin.executer.vo.ExperimentVO;
import br.ufpe.cin.executer.vo.PlanMigrationVO;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ExecuterService {

    private final JenkinsApiClient jenkinsApiClient;

    private final BudgetingApiClient budgetingApiClient;

    private final Executors executors;

    private final ExecutorMapper executorMapper;

    private final RemoveDeployService removeDeployService;

    public ExecuterService(final JenkinsApiClient jenkinsApiClient,
            final BudgetingApiClient budgetingApiClient, final Executors executors,
            final ExecutorMapper executorMapper, final RemoveDeployService removeDeployService) {
        this.jenkinsApiClient = jenkinsApiClient;
        this.budgetingApiClient = budgetingApiClient;
        this.executors = executors;
        this.executorMapper = executorMapper;
        this.removeDeployService = removeDeployService;
    }

    public void create(final ExperimentVO experimentVO) throws IOException {
        startedExperiment(experimentVO);
        jenkinsApiClient.firstDeploy(experimentVO.getPipelineName());
    }

    public void remove(final String pipelineName, final String nameProject) throws IOException {
        finishedExperiment(nameProject);
        jenkinsApiClient.remove(pipelineName);
    }

    public void executePlan(final PlanMigrationVO planMigrationVO) throws IOException {
        log.info(planMigrationVO.toString());

        final ActionEnum actionEnum = executedPlan(planMigrationVO);

        if (ActionEnum.MIGRATION.equals(actionEnum)) {
            budgetingApiClient.runScenario(planMigrationVO.getUpdate(), planMigrationVO.getNameProject());
            jenkinsApiClient.executePlanning(planMigrationVO);
        } else {
            log.info("Nao ocorreu a migracao.");
        }
    }

    public ExperimentEntity startedExperiment(final ExperimentVO experimentVO) {
        final ExperimentEntity experimentEntity = executorMapper.createExecutorEntity(experimentVO);
        experimentEntity.setRunning(RunningEnum.YES);

        return executors.save(experimentEntity);
    }

    public void finishedExperiment(final String nameProject) {
        final ExperimentEntity experimentEntity = findByNameProjectAndRunning(nameProject, RunningEnum.YES);

        final Optional<ExecutorEntity> executorRunning = experimentEntity.getExecutors().stream()
                .filter(executer -> RunningEnum.YES.equals(executer.getRunning())).findFirst();

        executorRunning.get().setRunning(RunningEnum.NO);

        final ExecutorEntity executorEntity = ExecutorEntity.builder()
                .running(RunningEnum.NO)
                .subject(RunningEnum.NO)
                .remove(executorRunning.get().getDeploy())
                .action(ActionEnum.END_EXPERIMENT)
                .build();

        experimentEntity.getExecutors().add(executorEntity);
        experimentEntity.setDhEnd(LocalDateTime.now());
        experimentEntity.setRunning(RunningEnum.NO);

        executors.save(experimentEntity);
    }

    public ActionEnum executedPlan(final PlanMigrationVO planMigrationVO) {
        final ExperimentEntity experimentEntity = findByNameProjectAndRunning(planMigrationVO.getNameProject(),
                RunningEnum.YES);

        final ExecutorEntity executorEntity = ExecutorEntity.builder()
                .deploy(planMigrationVO.getDeploy())
                .build();

        final Optional<ExecutorEntity> executorRunning = experimentEntity.getExecutors().stream()
                .filter(executer -> RunningEnum.YES.equals(executer.getRunning())).findFirst();

        if (planMigrationVO.getDeploy().equals(executorRunning.get().getDeploy())) {
            executorEntity.setRunning(RunningEnum.NO);
            executorEntity.setSubject(RunningEnum.NO);
        } else {
            executorEntity.setRunning(RunningEnum.YES);
            executorEntity.setSubject(RunningEnum.YES);
            executorEntity.setRemove(executorRunning.get().getDeploy());
            executorEntity.setAction(ActionEnum.MIGRATION);
            executorEntity.setDhAdaptation(LocalDateTime.now());

            executorRunning.get().setRunning(RunningEnum.NO);

            experimentEntity.setMigrationCount(experimentEntity.getMigrationCount() + 1);

            planMigrationVO.setRemove(executorRunning.get().getDeploy());

            removeDeployService.save(RemoveDeployEntity.builder()
                    .nameProject(planMigrationVO.getNameProject())
                    .deploy(planMigrationVO.getDeploy())
                    .remove(planMigrationVO.getRemove())
                    .running(RunningEnum.YES)
                    .build());
        }

        experimentEntity.getExecutors().add(executorEntity);

        executors.save(experimentEntity);

        return executorEntity.getAction();
    }

    public ExperimentEntity findByNameProjectAndRunning(final String nameProject, final RunningEnum runningEnum) {
        ExperimentEntity experimentEntity = executors.findByNameProjectAndRunningFetch(nameProject, runningEnum);

        if (experimentEntity == null) {
            throw new ExecutorNotFoundException();
        }

        return experimentEntity;
    }

}
