package com.emazon.ms_transaction.infra.out.jpa.entity;

import com.emazon.ms_transaction.ConsUtils;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;
import java.util.Set;

@Entity(name = "supply")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class SupplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = ConsUtils.FALSE, unique = true)
    private Long id;

    @Column(nullable = ConsUtils.FALSE)
    private Long userId;

    @JsonManagedReference
    @OneToMany(mappedBy = "supply", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<SupplyArticleEntity> supplyArticleEntity;

    @Column(nullable = ConsUtils.FALSE)
    private LocalDate transactionDate;

    public void addReferenceToSupplyArticlesEntities(Set<SupplyArticleEntity> supplyArticles) {
        supplyArticles.forEach(sa -> sa.setSupply(this));
    }
}
