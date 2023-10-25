package br.ufpe.cin.budgeting.repository;

import br.ufpe.cin.budgeting.model.BudgetingEntity;
import br.ufpe.cin.budgeting.model.RunningEnum;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Budgetings extends JpaRepository<BudgetingEntity, Long> {

    @Query(value = "SELECT bg FROM #{#entityName} bg " +
            "JOIN FETCH bg.microServices ms " +
            "JOIN FETCH ms.cloud cd " +
            // "JOIN FETCH cd.services " +
            "WHERE bg.id = :id")
    BudgetingEntity findFetchById(@Param("id") final Long id);

    @Query(value = "SELECT bg FROM #{#entityName} bg " +
            "JOIN FETCH bg.microServices ms " +
            "JOIN FETCH ms.cloud cd ")
    List<BudgetingEntity> fetchAll();

    Optional<BudgetingEntity> findByRunningEnumAndNameProject(final RunningEnum runningEnum, final String nameProject);

}
