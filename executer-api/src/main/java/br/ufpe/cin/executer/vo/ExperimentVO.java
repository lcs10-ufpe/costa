package br.ufpe.cin.executer.vo;

import br.ufpe.cin.executer.model.CloudProviderEnum;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExperimentVO {
    @NotBlank(message = "executor-1")
    private String nameProject;
    @NotBlank(message = "executor-2")
    private String experimentName;
    @NotBlank(message = "executor-3")
    private String pipelineName;
    @NotNull(message = "executor-4")
    private CloudProviderEnum deploy;
}
