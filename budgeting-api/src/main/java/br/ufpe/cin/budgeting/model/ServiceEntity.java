package br.ufpe.cin.budgeting.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "SERVICE")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ServiceEntity {

    @Id
    @SequenceGenerator(name = "SERVICE_SEQ", sequenceName = "SERVICE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICE_SEQ")
    @EqualsAndHashCode.Include
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "VALUE", precision = 10, scale = 2, nullable = false)
    private BigDecimal value;

    @Column(name = "TAG", nullable = false)
    private String tag;

    @Column(name = "INSTANCES", nullable = false)
    private Integer instances;

    @Column(name = "INSTANCE_TYPE", nullable = false)
    private String instanceType;

}
