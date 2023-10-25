package br.ufpe.cin.planner.resource;

import br.ufpe.cin.planner.service.PlannerService;
import br.ufpe.cin.planner.vo.MigrationCloudRequestVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plans")
public class PlannerResource {

    private final PlannerService plannerService;

    public PlannerResource(final PlannerService plannerService) {
        this.plannerService = plannerService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody final MigrationCloudRequestVO migrationCloudRequestVO) {
        plannerService.createPlanning(migrationCloudRequestVO);
    }

}
