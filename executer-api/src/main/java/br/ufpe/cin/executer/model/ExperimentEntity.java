package br.ufpe.cin.executer.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EXPERIMENT")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ExperimentEntity {

    @Id
    @SequenceGenerator(name = "EXPERIMENT_SEQ", sequenceName = "EXPERIMENT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXPERIMENT_SEQ")
    @EqualsAndHashCode.Include
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME_PROJECT", nullable = false)
    private String nameProject;

    @Column(name = "DATA_TIME_START", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime dhStart;

    @Column(name = "DATA_TIME_END", columnDefinition = "TIMESTAMP")
    private LocalDateTime dhEnd;

    @Column(name = "EXPERIMENT_NAME", nullable = false)
    private String experimentName;

    @Column(name = "MIGRATION_COUNT", nullable = false)
    private Integer migrationCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "RUNNING", length = 3)
    private RunningEnum running;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "EXECUTOR_ID", nullable = false)
    private List<ExecutorEntity> executors;

}
