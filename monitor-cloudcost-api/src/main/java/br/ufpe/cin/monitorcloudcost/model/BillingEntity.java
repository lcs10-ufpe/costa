package br.ufpe.cin.monitorcloudcost.model;

import java.math.BigDecimal;
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
@Table(name = "BILLING")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillingEntity {

    @Id
    @SequenceGenerator(name = "BILLING_SEQ", sequenceName = "BILLING_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BILLING_SEQ")
    @EqualsAndHashCode.Include
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME_PROJECT")
    private String nameProject;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACTIVE_BILLING_ENUM", length = 3)
    private ActiveBillingEnum activeBillingEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "CLOUD_PROVIDER", length = 30, nullable = false)
    private CloudProviderEnum cloudProviderEnum;

    @Column(name = "TOTAL_COST", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalCost;

    @Enumerated(EnumType.STRING)
    @Column(name = "CURRENCY", length = 6, nullable = false)
    private CurrencyEnum currencyEnum;

    @Column(name = "TAG")
    private String tag;

}
