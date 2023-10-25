package br.ufpe.cin.costestimation.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StackRequestVO {

    @NotBlank(message = "name")
    private String name;

    @NotBlank(message = "templateBody")
    private String templateBody;
}
