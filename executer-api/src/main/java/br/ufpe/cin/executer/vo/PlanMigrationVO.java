package br.ufpe.cin.executer.vo;

import br.ufpe.cin.executer.model.CloudProviderEnum;
import lombok.Data;

@Data
public class PlanMigrationVO {
    private String nameProject;
    private CloudProviderEnum deploy;
    private CloudProviderEnum remove;
    private Long update;

    @Override
    public String toString() {
        return "PlanMigrationVO(deploy_in_cloud=" + deploy + ", remove_in_cloud="
                + remove + ", new_scenario_running_budgeting=" + update + ")";
    }
}
