package br.ufpe.cin.monitorcloudcost.service;

import br.ufpe.cin.monitorcloudcost.model.BillingEntity;
import java.util.List;

public interface BillingService {

    void obtainBillingCloud();

    List<BillingEntity> all();

}
