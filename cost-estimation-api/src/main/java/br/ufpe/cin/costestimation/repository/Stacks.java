package br.ufpe.cin.costestimation.repository;

import br.ufpe.cin.costestimation.model.Stack;
import br.ufpe.cin.costestimation.model.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Stacks extends JpaRepository<Stack, Long> {

    List<Stack> findByType(final StatusType type);

    Optional<Stack> findByName(final String name);

}
