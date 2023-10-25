package br.ufpe.cin.planner.vo;

import br.ufpe.cin.planner.model.CloudProviderEnum;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class MigrationCloudRequestVO {
    private String nameProject;
    private CloudProviderEnum newCloudProviderEnum;
    private CloudProviderEnum oldCloudProviderEnum;
    private Long idNewScenarioBudgetingRunning;
}
