package br.ufpe.cin.monitorcloudcost.resource;

import br.ufpe.cin.monitorcloudcost.model.BillingEntity;
import br.ufpe.cin.monitorcloudcost.service.BillingService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billings")
public class BillingResource {

    private final BillingService billingService;

    public BillingResource(final BillingService billingService) {
        this.billingService = billingService;
    }

    @GetMapping("/get-cost-and-usage")
    public List<BillingEntity> all() {
        return billingService.all();
    }

}
