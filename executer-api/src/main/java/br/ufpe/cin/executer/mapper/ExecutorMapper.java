package br.ufpe.cin.executer.mapper;

import br.ufpe.cin.executer.model.ActionEnum;
import br.ufpe.cin.executer.model.ExecutorEntity;
import br.ufpe.cin.executer.model.ExperimentEntity;
import br.ufpe.cin.executer.model.RunningEnum;
import br.ufpe.cin.executer.vo.ExperimentVO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ExecutorMapper {

    public ExperimentEntity createExecutorEntity(final ExperimentVO experimentVO) {
        return ExperimentEntity.builder()
                .nameProject(experimentVO.getNameProject())
                .experimentName(experimentVO.getExperimentName())
                .executors(createExecutors(experimentVO))
                .dhStart(LocalDateTime.now())
                .migrationCount(0)
                .build();
    }

    private List<ExecutorEntity> createExecutors(final ExperimentVO experimentVO) {

        List<ExecutorEntity> executors = new ArrayList<>();

        executors.add(ExecutorEntity.builder()
                .running(RunningEnum.YES)
                .subject(RunningEnum.YES)
                .deploy(experimentVO.getDeploy())
                .action(ActionEnum.START_EXPERIMENT)
                .build());

        return executors;
    }

}
