package br.ufpe.cin.executer.repository;

import br.ufpe.cin.executer.model.RemoveDeployEntity;
import br.ufpe.cin.executer.model.RunningEnum;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoveDeploys extends JpaRepository<RemoveDeployEntity, Long> {

    Optional<RemoveDeployEntity> findByRunning(final RunningEnum running);

}
