package br.ufpe.cin.costestimation.vo;

import br.ufpe.cin.costestimation.model.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StackResponseVO {
    private String name;
    private String templateBody;
    private StatusType type;
    private String stackId;
}
