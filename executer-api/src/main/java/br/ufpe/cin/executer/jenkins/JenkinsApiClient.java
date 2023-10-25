package br.ufpe.cin.executer.jenkins;

import br.ufpe.cin.executer.model.CloudProviderEnum;
import br.ufpe.cin.executer.vo.PlanMigrationVO;
import java.io.IOException;

public interface JenkinsApiClient {
    void executePlanning(final PlanMigrationVO planMigrationVO) throws IOException;

    void firstDeploy(final String pipelineName) throws IOException;

    void remove(final String pipelineName) throws IOException;

    void removeDeployScheduler(final String nameProject, final CloudProviderEnum removeCloud)
            throws IOException;
}
