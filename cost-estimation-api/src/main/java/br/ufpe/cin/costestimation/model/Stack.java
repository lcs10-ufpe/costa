package br.ufpe.cin.costestimation.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Stack {

    @Id
    @SequenceGenerator(name = "stack_seq", sequenceName = "stack_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stack_seq")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "template_body", nullable = false)
    private String templateBody;

    @Column(name = "status_type", nullable = false)
    private StatusType type;

    @Column(name = "stack_id")
    private String stackId;

}
