package com.emazon.ms_transaction.infra.out.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Entity(name = "supply_article")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class SupplyArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "supply_id", nullable = false , updatable = false)
    private SupplyEntity supply;

    @Column(nullable = false, updatable = false)
    private Long articleId;

    @Column(nullable = false)
    private Integer quantity;
}
