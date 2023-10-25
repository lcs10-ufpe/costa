package br.ufpe.cin.budgeting.model;

import java.math.BigDecimal;
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
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "CLOUD")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CloudEntity {

    @Id
    @SequenceGenerator(name = "CLOUD_SEQ", sequenceName = "CLOUD_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLOUD_SEQ")
    @EqualsAndHashCode.Include
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "CLOUD_PROVIDER", length = 30, nullable = false)
    private CloudProviderEnum cloudProviderEnum;

    @Column(name = "REGION", nullable = false)
    private String region;

    @Column(name = "ZONE", nullable = false)
    private String zone;

    @Column(name = "AMOUNT", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "CLOUD_ID", nullable = false)
    private List<ServiceEntity> services;

}
