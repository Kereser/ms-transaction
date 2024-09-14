package com.emazon.ms_transaction.domain.model;

public class SupplyArticle {
    private Long id;
    private Supply supply;
    private Long articleId;
    private Long quantity;

    public SupplyArticle() {
    }

    public SupplyArticle(Long articleId, Long quantity) {
        this.articleId = articleId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supply getSupply() {
        return supply;
    }

    public void setSupply(Supply supply) {
        this.supply = supply;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}

