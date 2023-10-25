package br.ufpe.cin.budgeting.model;

import br.ufpe.cin.budgeting.serialize.LocalDateDeserializer;
import br.ufpe.cin.budgeting.serialize.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.math.BigDecimal;
import java.time.LocalDate;
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
@Table(name = "BUDGETING")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BudgetingEntity {

    @Id
    @SequenceGenerator(name = "BUDGETING_SEQ", sequenceName = "BUDGETING_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUDGETING_SEQ")
    @EqualsAndHashCode.Include
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME_PROJECT")
    private String nameProject;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "TOTAL_COST", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalCost;

    @Column(name = "CREATION", columnDefinition = "DATE", nullable = false)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate creation;

    @Enumerated(EnumType.STRING)
    @Column(name = "CURRENCY", length = 6, nullable = false)
    private CurrencyEnum currencyEnum;

    @Column(name = "EFFECTIVE_FROM", columnDefinition = "DATE", nullable = false)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate effectiveFrom;

    @Column(name = "EFFECTIVE_TO", columnDefinition = "DATE", nullable = false)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate effectiveTo;

    @Column(name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "PERIOD", length = 10, nullable = false)
    private PeriodEnum periodEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_TYPE", length = 10, nullable = false)
    private PaymentTypeEnum paymentTypeEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "RUNNING", length = 3, nullable = false)
    private RunningEnum runningEnum;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "BUDGETING_ID", nullable = false)
    private List<MicroServiceEntity> microServices;

}
