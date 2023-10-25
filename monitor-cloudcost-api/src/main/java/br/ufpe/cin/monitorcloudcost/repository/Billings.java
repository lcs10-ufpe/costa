package br.ufpe.cin.monitorcloudcost.repository;

import br.ufpe.cin.monitorcloudcost.model.BillingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Billings extends JpaRepository<BillingEntity, Long> {
}
