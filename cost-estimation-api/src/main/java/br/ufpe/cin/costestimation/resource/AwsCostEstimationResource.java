package br.ufpe.cin.costestimation.resource;

import br.ufpe.cin.costestimation.service.StackService;
import br.ufpe.cin.costestimation.vo.StackRequestVO;
import br.ufpe.cin.costestimation.vo.StackResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aws-cost-estimations")
public class AwsCostEstimationResource {

    private final StackService stackService;

    private AwsCostEstimationResource(final StackService stackService) {
        this.stackService = stackService;
    }

    @PostMapping
    public ResponseEntity<StackResponseVO> createCostEstimation(final StackRequestVO requestVO) {
        final StackResponseVO responseVO = stackService.createStack(requestVO);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseVO);
    }

    @GetMapping("/{name}")
    public ResponseEntity<StackResponseVO> findCostEstimationByName(final String name) {

        final StackResponseVO responseVO = stackService.findStackByName(name);

        return ResponseEntity.ok(responseVO);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(final String name) {
        stackService.delete(name);
    }

}
