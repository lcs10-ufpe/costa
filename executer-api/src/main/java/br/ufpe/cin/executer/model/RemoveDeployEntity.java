package br.ufpe.cin.executer.model;

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
@Table(name = "REMOVE_DEPLOY")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RemoveDeployEntity {

    @Id
    @SequenceGenerator(name = "REMOVE_DEPLOY_SEQ", sequenceName = "REMOVE_DEPLOY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REMOVE_DEPLOY_SEQ")
    @EqualsAndHashCode.Include
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME_PROJECT", nullable = false)
    private String nameProject;

    @Enumerated(EnumType.STRING)
    @Column(name = "DEPLOY_PROJECT_CLOUD", length = 30)
    private CloudProviderEnum deploy;

    @Enumerated(EnumType.STRING)
    @Column(name = "REMOVE_PROJECT_CLOUD  ", length = 30)
    private CloudProviderEnum remove;

    @Enumerated(EnumType.STRING)
    @Column(name = "RUNNING", length = 3)
    private RunningEnum running;

}
