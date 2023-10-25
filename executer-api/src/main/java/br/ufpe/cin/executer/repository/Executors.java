package br.ufpe.cin.executer.repository;

import br.ufpe.cin.executer.model.ExperimentEntity;
import br.ufpe.cin.executer.model.RunningEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Executors extends JpaRepository<ExperimentEntity, Long> {

    // @Query(value = "SELECT ex FROM #{entityName} ex" +
    // "JOIN FETCH ex.migrations mi " +
    // "WHERE ex.nameProject = :nameProject " +
    // "AND mi.runningEnum = :runningEnum")
    // Optional<ExperimentEntity> findByByNameProjectAndRunningEnum(@Param("nameProject") final String nameProject,
    // @Param("runningEnum") final RunningEnum runningEnum);

    @Query(value = "SELECT exp FROM #{#entityName} exp " +
            "JOIN FETCH exp.executors exe " +
            "WHERE exp.nameProject = :nameProject " +
            "AND exp.running = :runningEnum ")
    ExperimentEntity findByNameProjectAndRunningFetch(@Param("nameProject") final String nameProject,
            @Param("runningEnum") final RunningEnum runningEnum);

}
