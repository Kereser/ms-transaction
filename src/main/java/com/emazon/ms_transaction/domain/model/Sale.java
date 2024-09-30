package com.emazon.ms_transaction.domain.model;

import java.time.LocalDateTime;
import java.util.Set;

public class Sale {
    private Long id;
    private Long userId;
    private Set<SaleArticle> saleArticles;
    private LocalDateTime transactionDate = LocalDateTime.now();
    private SaleStatus status;

    public Sale() {
    }

    public Sale(Long id, Long userId, Set<SaleArticle> saleArticles, LocalDateTime transactionDate, SaleStatus status) {
        this.id = id;
        this.userId = userId;
        this.saleArticles = saleArticles;
        this.transactionDate = transactionDate;
        this.status = status;
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

    public Set<SaleArticle> getSaleArticles() {
        return saleArticles;
    }

    public void setSaleArticles(Set<SaleArticle> saleArticles) {
        this.saleArticles = saleArticles;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public SaleStatus getStatus() {
        return status;
    }

    public void setStatus(SaleStatus status) {
        this.status = status;
    }
}
