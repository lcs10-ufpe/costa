package br.ufpe.cin.executer.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EXECUTOR")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ExecutorEntity {

    @Id
    @SequenceGenerator(name = "EXECUTOR_SEQ", sequenceName = "EXECUTOR_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXECUTOR_SEQ")
    @EqualsAndHashCode.Include
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "RUNNING", length = 3, nullable = false)
    private RunningEnum running;

    @Enumerated(EnumType.STRING)
    @Column(name = "SUBJECT", length = 3)
    private RunningEnum subject;

    @Enumerated(EnumType.STRING)
    @Column(name = "DEPLOY_PROJECT_CLOUD", length = 30)
    private CloudProviderEnum deploy;

    @Enumerated(EnumType.STRING)
    @Column(name = "REMOVE_PROJECT_CLOUD  ", length = 30)
    private CloudProviderEnum remove;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACTION", length = 30)
    private ActionEnum action;

    @Column(name = "DATA_TIME_ADAPTATION", columnDefinition = "TIMESTAMP")
    private LocalDateTime dhAdaptation;

}
