package br.ufpe.cin.executer.resource;

import br.ufpe.cin.executer.service.ExecuterService;
import br.ufpe.cin.executer.vo.ExperimentVO;
import br.ufpe.cin.executer.vo.PlanMigrationVO;
import java.io.IOException;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/executors")
public class ExecuterResource {

    private final ExecuterService executerService;

    public ExecuterResource(final ExecuterService executerService) {
        this.executerService = executerService;
    }

    @PostMapping("/first-deploy")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void deploy(@RequestBody @Valid final ExperimentVO experimentVO) throws IOException {
        executerService.create(experimentVO);
    }

    @DeleteMapping("/remove-deploy/{pipelineName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("pipelineName") final String pipelineName,
            @RequestParam("nameProject") final String nameProject) throws IOException {
        executerService.remove(pipelineName, nameProject);
    }

    @PostMapping("/plan")
    @ResponseStatus(value = HttpStatus.OK)
    public void execute(@RequestBody final PlanMigrationVO planMigrationVO) throws IOException {
        executerService.executePlan(planMigrationVO);
    }

}
