package com.emazon.ms_transaction.domain.model;

import java.time.LocalDate;
import java.util.Set;

public class Supply {
    private Long id;
    private Long userId;
    private Set<SupplyArticle> supplyArticle;
    private LocalDate transactionDate = LocalDate.now();

    public Supply() {
    }

    public Supply(Long id, Long userId, Set<SupplyArticle> supplyArticle) {
        this.id = id;
        this.userId = userId;
        this.supplyArticle = supplyArticle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<SupplyArticle> getSupplyArticle() {
        return supplyArticle;
    }

    public void setSupplyArticle(Set<SupplyArticle> supplyArticle) {
        this.supplyArticle = supplyArticle;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}
