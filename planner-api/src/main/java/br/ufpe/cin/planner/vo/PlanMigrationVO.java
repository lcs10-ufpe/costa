package br.ufpe.cin.planner.vo;

import br.ufpe.cin.planner.model.CloudProviderEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanMigrationVO {
    private String nameProject;
    private CloudProviderEnum deploy;
    private CloudProviderEnum remove;
    private Long update;

    @Override
    public String toString() {
        return "PlanMigrationVO(deploy_in_cloud=" + deploy + ", remove_in_cloud="
                + remove + ", new_scenario_budgeting=" + update + ")";
    }
}
