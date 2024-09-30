package com.emazon.ms_transaction.infra.out.jpa.entity;

import com.emazon.ms_transaction.ConsUtils;
import com.emazon.ms_transaction.domain.model.SaleStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "sales")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = ConsUtils.FALSE)
    private Long userId;

    @JsonManagedReference
    @OneToMany(mappedBy = "sale", orphanRemoval = ConsUtils.TRUE, cascade = CascadeType.ALL)
    @Column(nullable = false)
    private Set<SaleArticleEntity> saleArticles;

    @Builder.Default
    @Column(nullable = ConsUtils.FALSE)
    private LocalDateTime transactionDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = ConsUtils.FALSE)
    private SaleStatus status;

    public void addReferenceToSaleArticles(Set<SaleArticleEntity> saleArticles) {
        saleArticles.forEach(sa -> sa.setSale(this));
    }
}


