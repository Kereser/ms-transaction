package com.emazon.ms_transaction.infra.out.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "sale_article")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false , updatable = false)
    private SaleEntity sale;

    @Column(nullable = false, updatable = false)
    private Long articleId;

    @Column(nullable = false, updatable = false)
    private Long quantity;
}
